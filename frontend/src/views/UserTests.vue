<template>
  <v-container>
    <div>
      <v-data-table
          :headers="headers"
          :items="data.content"
          :options.sync="options"
          :server-items-length="data.totalElements"
          :loading="loading"
          show-select
          class="elevation-1"
      >
        <template v-slot:item.available="{ item }">

          <v-icon v-if="item.available" color="success">mdi-check-circle-outline</v-icon>
          <v-icon v-if="!item.available" color="error">mdi-close-circle-outline</v-icon>
        </template>
      </v-data-table>
    </div>
  </v-container>
</template>

<script>
export default {
  name: "UserTests",
  data() {
    return {
      data: {},
      totalTests: 0,
      tests: [],
      loading: true,
      options: {},
      headers: [
        {
          text: 'Title',
          value: 'title',
          class: 'my-header-style'
        },
        {text: 'Test creator', value: 'creatorName'},
        {text: 'Is available', value: 'available'},
        {text: 'Available from date', value: 'availableFrom'},
        {text: 'Available to date', value: 'availableTo'},
        {text: 'Number of questions', value: 'questionsCount'},
      ],
    }
  },
  watch: {
    options: {
      handler() {
        this.getDataFromApi()
      },
      deep: true,
    },
  },
  methods: {
    getDataFromApi() {
      console.log(this.options)
      const pageParam = `?size=${this.options.itemsPerPage}&page=${this.options.page - 1}`;
      let sortParam = "";

      if (this.options.sortBy.length > 0) {
        sortParam = "&sort=" + this.options.sortBy[0] + "," + (this.options.sortDesc[0] === false ? "ASC" : "DESC");
      }
      console.log(pageParam + sortParam);
      this.loading = true
      this.$store.dispatch("test/getTestsForCurrentUser", pageParam + sortParam).then(data => {
        console.log(data);
        this.data = data;
        this.loading = false
      })
    },
  },
}
</script>

<style scoped>
.v-data-table >>> .v-data-table-header > tr > th{
  background-color: #226582 !important;
  color: white !important;
  font-size: 14px !important;
}
.v-data-table >>> .v-data-table-header > tr > th > i{
  background-color: #226582 !important;
  color: white !important;
  font-size: 14px !important;
}
</style>