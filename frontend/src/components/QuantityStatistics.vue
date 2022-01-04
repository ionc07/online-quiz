<template>
  <v-card
      elevation="2"
      class="pb-6"
  >
    <v-card-title>Quantitative statistics</v-card-title>
    <div class="qty-statistics">
      <v-icon>mdi-account-multiple</v-icon>
      Users - {{ qtyStats.usersCount }}
    </div>
    <div class="qty-statistics">
      <v-icon>mdi-clipboard-text</v-icon>
      Tests - {{ qtyStats.testsCount }}
    </div>
    <div class="qty-statistics">
      <v-icon>mdi-calendar</v-icon>
      Events - {{ qtyStats.testEventCount }}
    </div>
  </v-card>
</template>

<script>
export default {
  name: "QuantityStatistics",
  data() {
    return {
      qtyStats: {usersCount: 0},
      qtyStatsItems: [{
        field: "usersCount",
        name: "Users",
        icon: "mdi-account-multiple",
        value: 0
      },
        {
          field: "testsCount",
          icon: "mdi-clipboard-text",
          value: 0
        },
        {
          field: "testEventCount",
          icon: "mdi-calendar",
          value: 0
        }
      ],
    }
  },
  methods: {
    fetchStatistics() {
      this.$store
          .dispatch("getStatistics")
          .then((response) => {
            console.log(response)
            this.qtyStats = response.data;
          })
          .catch(error => {
            console.log("Error to login!");
            console.log(error);

            this.loading = false;

          });
    }
  },
  beforeMount() {
    this.fetchStatistics();
  },

}
</script>

<style scoped>
.qty-statistics {
  display: inline;
  margin-left: 2rem;
}
</style>