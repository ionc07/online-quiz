<template>
  <v-dialog
      v-model="dialog"
      fullscreen
      hide-overlay
      transition="dialog-bottom-transition"
  >
    <v-card>
      <v-toolbar dark class="test-response-toolbar">
        <v-toolbar-title>{{ test.title }}</v-toolbar-title>
        <v-spacer></v-spacer>
        <v-btn icon @click="closeDialog">
          <v-icon color="white">mdi-close</v-icon>
        </v-btn>
      </v-toolbar>
      <v-card class="mb-4">
        <v-card-title>
          {{ test.title }}
        </v-card-title>
      </v-card>

      <v-row v-for="(question, index) in test.questions" :key="question.id" class="mb-4">
        <v-col>
          <v-card>
            <v-card-title>
              <span class="question-text">{{ question.sequence + 1 }}. {{ question.value }}</span>
              <v-chip color="info" text-color="white" class="ml-2">{{ question.answerType }}</v-chip>
            </v-card-title>
            <v-card-text>
              <v-list dense>
                <v-list-item v-for="answer in question.answers" :key="answer.id">
                  <v-list-item-content>
                    <v-list-item-title>
                    <span :style="getAnswerStyle(question, answer)" class="answer-text">
                      {{ answer.value }}
                    </span>
                      <v-icon small v-if="isUserSelectedAnswer(question, answer)" color="primary">mdi-check</v-icon>
                    </v-list-item-title>
                    <v-list-item-subtitle v-if="isUserSelectedAnswer(question, answer)">
                      <span v-if="answer.correct" class="green--text">Correct</span>
                      <span v-else class="red--text">Incorrect</span>
                    </v-list-item-subtitle>
                  </v-list-item-content>
                </v-list-item>
              </v-list>
            </v-card-text>
          </v-card>
        </v-col>
      </v-row>
    </v-card>
  </v-dialog>
</template>

<script>
export default {
  props: {
    dialog: {
      type: Boolean,
      required: true
    },
    testDataAndResponses: {
      type: Object,
      required: true
    }
  },
  computed: {
    test() {
      console.log("Created test response dialog");
      console.log(this.testDataAndResponses);
      return this.testDataAndResponses.test;
    },
    userResponses() {
      return this.testDataAndResponses.userResponses;
    }
  },
  methods: {
    isUserSelectedAnswer(question, answer) {
      const userResponse = this.userResponses.find(
          response => response.questionId === question.id
      );
      return userResponse ? userResponse.selectedAnswerIds.includes(answer.id) : false;
    },
    isQuestionIncorrect(question) {
      if (question.answerType === 'checkbox') {
        const userResponse = this.userResponses.find(
            response => response.questionId === question.id
        );
        if (!userResponse) return false;
        return userResponse.selectedAnswerIds.some(answerId => {
          const answer = question.answers.find(ans => ans.id === answerId);
          return answer ? !answer.correct : false;
        });
      }
      return false;
    },
    getAnswerStyle(question, answer) {
      if (this.isUserSelectedAnswer(question, answer)) {
        return answer.correct ? 'color: green' : 'color: red';
      }
      return '';
    },
    closeDialog() {
      this.dialog = false;
      this.$emit('closeDialog');
    }
  }
};
</script>

<style scoped>
.v-expansion-panel-header {
  cursor: pointer;
}

.test-response-toolbar {
  background-color: #226582 !important;
}

.question-text {
  font-size: 1.25rem; /* Example: 1.25rem or 20px */
}

/* Increase the font size for the answers */
.answer-text {
  font-size: 1.125rem; /* Example: 1.125rem or 18px */
}

/* Optional: Adjust the size of the chip if needed */
.title-with-chip .v-chip {
  font-size: 0.875rem; /* Example: 0.875rem or 14px */
}
</style>
