<template>
  <v-container>
    <ViewTestResponseDialog :dialog="showTestResultDialog" :testDataAndResponses="testDetailsAndResponse"
                            @closeDialog="closeTestResponseDialog"/>
    <v-row class="mb-1 mt-1">
      <v-col>
        <v-dialog
            v-model="deleteDialog"
            persistent
            max-width="290"
        >
          <template v-slot:activator="{ on, attrs }">
            <v-btn v-bind="attrs" v-on="on" color="error"
                   :disabled="selectedTestResults.length === 0">Delete
              <v-icon dense right color="white">mdi-delete</v-icon>
            </v-btn>
          </template>
          <v-card>
            <v-card-title class="text-h6">
              Are you sure you want to delete the selected test results?
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
                  @click="deleteSelectedTestResults"
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
        :items="testResults.content"
        item-key="id"
        :options.sync="options"
        :server-items-length="testResults.totalElements"
        :loading="loading"
        loading-text="Loading... Please wait"
        show-select
        v-model="selectedTestResults"
        class="elevation-1">
      <template v-slot:item.userShortDetails="{ item }">
        {{ item.userShortDetails.firstName + ' ' + item.userShortDetails.lastName }}
      </template>
      <template v-slot:item.obtainedScore="{ item }">
        {{ item.totalScore + '/' + item.score }}
      </template>
      <template v-slot:item.correctQuestions="{ item }">
        {{ item.correctQuestions + '/' + item.totalQuestions }}
      </template>
      <template v-slot:item.viewResponses="{ item }">
        <v-btn
            rounded
            color="success"
            @click="fetchTestAndUserResponse(item.userShortDetails.id, item.testId)"
            :loading="viewResponseLoading"
            :disabled="viewResponseLoading"
        > View
          <v-icon right color="white">mdi-file-eye</v-icon>
        </v-btn>
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
      {{ "The selected test results were successfully deleted!" }}

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
      {{ "Failed to delete the selected test results!" }}

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

  </v-container>
</template>

<script>
import TestResultService from "@/services/testResult.service";
import ViewTestResponseDialog from "@/views/ViewTestResponseDialog.vue";
import UserResponseService from "@/services/userResponse.service";

export default {
  name: "TestResults",
  components: {ViewTestResponseDialog},
  data() {
    return {
      testGroups: [],
      movedToGroupSuccess: false,
      testResults: [],
      testsShortData: [],
      totalTests: 0,
      tests: [],
      loading: true,
      options: {},
      headers: [
        {
          text: 'Title',
          value: 'testName',
        },
        {text: 'User', value: 'userShortDetails'},
        {text: 'Score', value: 'obtainedScore'},
        {text: 'Questions', value: 'correctQuestions'},
        {text: 'Taken at', value: 'takenAt'},
        {text: 'View responses', value: 'viewResponses'},

      ],
      selectedTestResults: [],
      deleteDialog: false,
      deletionSuccess: false,
      deletionFailure: false,
      showTestResultDialog: false,
      testResultData: {},
      viewResponseLoading: false,
      testDetailsAndResponse: {}
    }
  },
  watch: {
    options: {
      handler() {
        this.fetchTestResults()
      }
      ,
      deep: true,
    }
    ,
  }
  ,
  methods: {
    fetchTestResults() {
      console.log(this.options)
      const params = `?size=${this.options.itemsPerPage}&page=${this.options.page - 1}`;

      console.log(params);
      this.loading = true
      TestResultService.getTestResults(params).then(response => {
        this.testResults = response.data;
        console.log(response.data);
        this.loading = false;
      }).catch(error => {
        console.log(error);
        this.loading = false;
      });

      this.$store.dispatch("test/getAllTestsShortDetails").then(data => {
        console.log(data);
        this.testShortData = data;
      });
    },
    fetchTestAndUserResponse(userId, testId) {
      this.viewResponseLoading = true;
      UserResponseService.getUserResponsesByUserIdAndTestId(userId, testId).then(response => {
        console.log(response.data);
        this.testDetailsAndResponse = response.data;
        this.viewResponseLoading = false;
        this.openTestResultDialog(this.testDetailsAndResponse);
      }).catch(error => {
        console.log(error);
        this.viewResponseLoading = false;
      })
    },
    openTestResultDialog(testDetailsAndResponse) {
      this.testDetailsAndResponse = testDetailsAndResponse;
      this.showTestResultDialog = true;
    },
    closeTestResponseDialog() {
      console.log("Closing test user response dialog...");
      this.showTestResultDialog = false;
    },
    deleteSelectedTestResults() {
      console.log("Deleting selected tests");
      console.log(this.selectedTestResults);
      if (this.selectedTestResults.length > 0) {

        TestResultService.deleteTestResults(this.selectedTestResults.map((testResult) => {
          return testResult.id
        }).join(',')).then(response => {
          this.deletionSuccess = true;
          this.fetchTestResults();
        }).catch(error => {
          this.deletionFailure = true;
        })
      }
      this.deleteDialog = false;
    },
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