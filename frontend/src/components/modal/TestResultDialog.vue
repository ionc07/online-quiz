<template>
  <v-app>
    <v-dialog persistent v-model="show" width="600">
      <v-card>
        <v-card-title class="justify-center">
          {{ testResultData.testName }}

        </v-card-title>
        <v-divider></v-divider>
        <v-card-text class="mt-2">
          <div class="test-result-item">
            <span class="test-result-info">Obtained test score:</span>
            <h3>{{ testResultData.totalScore + '/' + testResultData.score }}</h3>
          </div>
          <div class="test-result-item">
            <span class="test-result-info">Correct questions :</span>
            <h3>{{ testResultData.correctQuestions + '/' + testResultData.totalQuestions }}</h3>
          </div>
          <div class="test-result-item">
            <span class="test-result-info">Submit time:</span>
            <h3>{{ getSubmitDateTime() }}</h3>
          </div>
        </v-card-text>
        <v-card-actions class="justify-center pb-5">
          <v-btn class="default-btn" @click="$emit('closeDialog')">Close</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-app>
</template>

<script>
export default {
  props: {
    show: {
      type: Boolean,
      required: true
    },
    testResultData: {
      type: Object,
      required: true
    }
  },
  data() {
    return {
    };
  },
  methods: {
    getSubmitDateTime() {
      // Get current date and time
      const now = new Date();

      // Format the date and time
      return `${now.getFullYear()}-${(now.getMonth() + 1).toString().padStart(2, '0')}-${now.getDate().toString().padStart(2, '0')} ${now.getHours().toString().padStart(2, '0')}:${now.getMinutes().toString().padStart(2, '0')}:${now.getSeconds().toString().padStart(2, '0')}`
    },
  }
};
</script>

<style>
.test-result-info {
  font-size: 16px;
}

.test-result-item {
  display: flex;
  align-items: center;
  margin-bottom: 10px; /* Add margin between items */
}

.test-result-item h3 {
  font-weight: bold;
  margin-left: 10px; /* Add space between span and h3 */
}

.default-btn {
  background-color: #226582 !important;
  color: white !important;
}
</style>
