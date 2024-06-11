<template>
  <v-container>
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

      </v-col>
    </v-row>
    <v-data-table
        :headers="headers"
        :items="data.content"
        item-key="testDetails.id"
        :options.sync="options"
        :server-items-length="data.totalElements"
        :loading="loading"
        loading-text="Loading... Please wait"
        show-select
        v-model="selectedTests"
        class="elevation-1"
    >
      <template v-slot:item.testDetails.available="{ item }">

        <v-icon v-if="item.testDetails.available" color="success">mdi-check-circle-outline</v-icon>
        <v-icon v-if="!item.testDetails.available" color="error">mdi-close-circle-outline</v-icon>
      </template>

      <template v-slot:item.takeTest="{item}">
        <div v-if="!item.testResult">
          <v-btn
              outlined
              rounded
              color="success"
              @click="goToTestTakingPage(item.testDetails.id)"
          >
            <v-icon color="success">mdi-play</v-icon>
          </v-btn>
        </div>
        <div v-else>
          <v-tooltip bottom>
            <template v-slot:activator="{ on, attrs }">
              <v-btn
                  outlined
                  rounded
                  color="error"
                  v-bind="attrs"
                  v-on="on"
                  @click="openTestResultDialog(item.testResult)"
              >
                <v-icon color="error">mdi-information-slab-circle</v-icon>
              </v-btn>
            </template>
            <span>Taken at: {{ item.testResult.takenAt }}	</span>
          </v-tooltip>

        </div>
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
    <TestResultDialog :testResultData="testResultData" :show="showTestResultDialog"
                      @closeDialog="closeTestResultDialog"/>
  </v-container>
</template>

<script>
import TestResultDialog from "@/components/modal/TestResultDialog.vue";

export default {
  name: "UserTests",
  components: {TestResultDialog},
  data() {
    return {
      testGroups: [],
      movedToGroupSuccess: false,
      data: {},
      totalTests: 0,
      tests: [],
      loading: true,
      options: {},
      headers: [
        {
          text: 'Title',
          value: 'testDetails.title',
          class: 'my-header-style'
        },
        {text: 'Test creator', value: 'testDetails.creatorName'},
        {text: 'Is available', value: 'testDetails.available'},
        {text: 'Available from date', value: 'testDetails.availableFrom'},
        {text: 'Available to date', value: 'testDetails.availableTo'},
        {text: 'Test group', value: 'testDetails.testGroupName'},
        {text: 'Take test', value: 'takeTest'},
      ],
      selectedTests: [],
      deleteDialog: false,
      deletionSuccess: false,
      deletionFailure: false,
      showTestResultDialog: false,
      testResultData: {}
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
      this.$store.dispatch("test/getSharedTestsForCurrentUser", pageParam + sortParam).then(data => {
        console.log(data);
        this.data = data;
        this.loading = false
      })

      this.$store.dispatch("testGroup/getAllTestGroups").then(data => {
        console.log(data);
        this.testGroups = data;
      });
    },
    openTestResultDialog(testResult) {
      this.testResultData = testResult;
      this.showTestResultDialog = true;
    },
    closeTestResultDialog() {
      this.showTestResultDialog = false;
      this.testResultData = {};
    },
    deleteSelectedTests() {
      console.log("Deleting selected tests");
      console.log(this.selectedTests);
      if (this.selectedTests.length > 0) {
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
      this.deleteDialog = false;
    },
    goToTestTakingPage(testId) {
      this.$router.push({name: 'Take test', params: {id: testId}});
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

.test-group-list-item:hover {
  cursor: pointer;
  background-color: rgba(162, 162, 162, 0.26);
}
</style>