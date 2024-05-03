<template>
  <v-container>
    <div>
      <v-row class="mb-1 mt-1">
        <v-col>
          <v-dialog
              v-model="deleteDialog"
              persistent
              max-width="290"
          >
            <template v-slot:activator="{ on, attrs }">
              <v-btn v-bind="attrs" v-on="on" color="error"
                     :disabled="selectedTests.length === 0">Delete
                <v-icon dense right color="white">mdi-delete</v-icon>
              </v-btn>
            </template>
            <v-card>
              <v-card-title class="text-h6">
                Are you sure you want to delete the selected tests?
              </v-card-title>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn
                    color="red"
                    text
                    @click="deleteDialog = false"
                >
                  Cancel
                </v-btn>
                <v-btn
                    color="error"
                    @click="deleteSelectedTests"
                >
                  Yes
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>


          <v-btn color="success" class="ml-4" @click="moveSelectedToGroup" :disabled="selectedTests.length === 0">Move
            to Group
            <v-icon dense right color="white">mdi-file-document-arrow-right-outline</v-icon>
          </v-btn>

          <v-btn color="primary" class="ml-4" @click="moveSelectedToGroup" :disabled="selectedTests.length === 0">
            Share
            <v-icon dense right color="white">mdi-share</v-icon>
          </v-btn>

        </v-col>
      </v-row>
      <v-data-table
          :headers="headers"
          :items="data.content"
          :options.sync="options"
          :server-items-length="data.totalElements"
          :loading="loading"
          loading-text="Loading... Please wait"
          show-select
          v-model="selectedTests"
          class="elevation-1"
      >
        <template v-slot:item.available="{ item }">

          <v-icon v-if="item.available" color="success">mdi-check-circle-outline</v-icon>
          <v-icon v-if="!item.available" color="error">mdi-close-circle-outline</v-icon>
        </template>
      </v-data-table>
      <v-snackbar
          v-model="deletionSuccess"
          :timeout="3000"
          centered
          top
          color="success"
          elevation="24"
          :multi-line="true"
      >
        {{ "The selected tests were successfully deleted!" }}

        <template v-slot:action="{ attrs }">
          <v-btn
              text
              v-bind="attrs"
              @click="deletionSuccess = false"
          >
            Close
          </v-btn>
        </template>
      </v-snackbar>
      <v-snackbar
          v-model="deletionFailure"
          :timeout="3000"
          centered
          top
          color="error"
          elevation="24"
          :multi-line="true"
      >
        {{ "Failed to delete the selected tests!" }}

        <template v-slot:action="{ attrs }">
          <v-btn
              text
              v-bind="attrs"
              @click="deletionFailure = false"
          >
            Close
          </v-btn>
        </template>
      </v-snackbar>
    </div>
  </v-container>
</template>

<script>
export default {
  name: "UserTests",
  data() {
    return {
      selectedView: 'grid',
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
      selectedTests: [],
      deleteDialog: false,
      deletionSuccess: false,
      deletionFailure: false,
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
    switchToGridView() {
      this.selectedView = 'grid'; // Set selected view to grid
    },
    switchToListView() {
      this.selectedView = 'list'; // Set selected view to list
    },
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
    deleteSelectedTests() {
      if (this.selectedTests.length > 0) {
        console.log("Deleting selected tests");
        console.log(this.selectedTests);
        this.$store.dispatch('test/deleteTests', this.selectedTests.map((test) => {
          return test.id
        }).join(','))
            .then(() => {
              this.deleteDialog = false;
              this.deletionSuccess = true;
              this.getDataFromApi();
            })
            .catch(error => {
              console.log("Error deleting tests!");
              console.log(error);
              console.log(error.response)

              this.deleteDialog = false;
              this.deletionFailure = true;
            });
      }
    },
    moveSelectedToGroup() {
      console.log("Moving selected tests");
      console.log(this.selectedTests);
    }
  },
}
</script>

<style scoped>
.v-data-table >>> .v-data-table-header > tr > th {
  background-color: #226582 !important;
  color: white !important;
  font-size: 14px !important;
}

.v-data-table >>> .v-data-table-header > tr > th > i {
  background-color: #226582 !important;
  color: white !important;
  font-size: 14px !important;
}
</style>