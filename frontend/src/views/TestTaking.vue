<template>
  <div>
    <v-container>
      <v-row>
        <v-col
            cols="9"
        >
          <v-card
              class="pa-2"
              tile
          >
            <h1>{{ testData.title }}</h1>
            <v-row v-if="isTestAvailable">
              <v-col>
                <p>Test is available from: {{ testData.settings.availableFrom }}</p>
                <p v-if="testData.settings.availableTo">Test is available to: {{ testData.settings.availableTo }}</p>
              </v-col>
            </v-row>
            <v-row>
              <v-col>
                <h2>{{ currentQuestion.value }}</h2>
                <div v-if="currentQuestion.answerType === 'radio'">
                  <v-radio-group v-model="userResponses[currentIndex].answer" @change="markQuestionAsAnswered">
                    <v-radio v-for="(answer) in currentQuestion.answers" :key="answer.id"
                             :label="answer.value"
                             :value="answer"></v-radio>
                  </v-radio-group>
                </div>
                <div v-if="currentQuestion.answerType === 'checkbox'">
                  <v-checkbox v-for="(answer) in currentQuestion.answers" :key="answer.id"
                              :label="answer.value" :value="answer"
                              v-model="userResponses[currentIndex].answer"
                              @change="markQuestionAsAnswered"></v-checkbox>
                </div>
                <div v-if=" currentQuestion.answerType === 'dropdown'">
                  <v-select v-model="userResponses[currentIndex].answer"
                            :items="currentQuestion.answers.map((answer) => ({ text: answer.value, value: answer }))"
                            label="Select an answer" @change="markQuestionAsAnswered"></v-select>
                </div>
              </v-col>
            </v-row>
            <v-row>
              <v-col cols="auto">
                <v-btn @click="prevQuestion" :disabled="currentIndex === 0">Previous</v-btn>
              </v-col>
              <v-col cols="auto">
                <span>{{ currentIndex + 1 }} / {{ testData.questions.length }}</span>
              </v-col>
              <v-col cols="auto">
                <v-btn @click="nextQuestion" :disabled="currentIndex === testData.questions.length - 1">Next</v-btn>
              </v-col>
              <v-spacer></v-spacer>
            </v-row>
          </v-card>
        </v-col>

        <!-- Question index with checkboxes -->
        <v-col
            cols="3"
        >
          <v-card
              class="pa-2"
              tile
          >
            <v-row>
              <v-col cols="3" v-for="(question, index) in testData.questions" :key="index">
                <v-btn small @click="loadQuestion(index)" :color="index === currentIndex ? 'primary' : ''">
                  {{ index + 1 + '.' }}
                  <v-icon :color="index === currentIndex ? 'white' : ''" v-if="answeredQuestions.includes(index)">
                    mdi-checkbox-marked
                  </v-icon>
                  <v-icon :color="index === currentIndex ? 'white' : ''" v-else>mdi-checkbox-blank-outline</v-icon>
                </v-btn>
              </v-col>
            </v-row>
            <v-row justify="center">
              <v-col cols="auto">
                <v-btn
                    depressed
                    color="success"
                    @click="submitTest"
                    :loading="submitLoading"
                    :disabled="submitLoading || answeredQuestions.length === 0"
                >
                  Submit
                </v-btn>
              </v-col>
            </v-row>
          </v-card>
        </v-col>
      </v-row>
      <TestResultDialog :show="showTestResultDialog" :testResultData="testResultData"
                        @closeDialog="closeTestResultDialog"/>
    </v-container>
  </div>
</template>

<script>

import UserResponseService from "@/services/userResponse.service";
import TestResultService from "@/services/testResult.service";
import TestResultDialog from "@/components/modal/TestResultDialog.vue";

export default {
  components: {TestResultDialog},
  data() {
    return {
      testData: {},
      currentIndex: 0,
      selectedAnswerIndices: [],
      selectedAnswers: [],
      answeredQuestions: [],
      userResponses: [],
      selected: [],
      submitLoading: false,
      showTestResultDialog: false,
      testResultData: {}
    };
  },
  computed: {
    currentQuestion() {
      return this.testData.questions[this.currentIndex];
    },
    isTestAvailable() {
      if (this.testData.settings) {
        const currentTime = new Date().getTime();
        const availableFrom = new Date(this.testData.settings.availableFrom).getTime();
        const availableTo = this.testData.settings.availableTo ? new Date(this.testData.settings.availableTo).getTime() : null;
        return currentTime >= availableFrom && (availableTo === null || currentTime <= availableTo);
      }
      return false;
    },
  },
  methods: {
    prevQuestion() {
      if (this.currentIndex > 0) {
        this.currentIndex--;
      }
    },
    nextQuestion() {
      if (this.currentIndex < this.testData.questions.length - 1) {
        this.currentIndex++;
      }
    },
    loadQuestion(index) {
      this.currentIndex = index;
    },
    closeTestResultDialog() {
      this.showTestResultDialog = false;
      this.$router.push('/sharedTests');
    },
    markQuestionAsAnswered() {
      console.log(JSON.stringify(this.selectedAnswerIndices));
      console.log(JSON.stringify(this.selectedAnswers));
      if (!this.answeredQuestions.includes(this.currentIndex)) {
        this.answeredQuestions.push(this.currentIndex);
      }

      console.log(this.userResponses);
    },
    submitTestResult() {
      const testId = this.$route.params.id;
      const totalQuestions = this.userResponses.length;

      let totalScore = 0;
      let score = 0;
      let correctQuestions = 0;

      this.testData.questions.forEach((question) => {
        question.answers.forEach((answer) => {
          if (answer.correct) {
            score += answer.score;
          }
        })
      })

      let responseIdx = 0;
      this.userResponses.forEach((userResponse) => {
        // Skip question if an answer was not provided
        if (userResponse.answer.length === 0) {
          return;
        }
        // CheckBox questions can have multiple selected answers
        if (userResponse.answerType === 'checkbox') {
          let question = this.testData.questions[responseIdx];
          let questionScore = 0;
          question.answers.forEach((answer) => {
            if (answer.correct && userResponse.answer.includes(answer) ||
                !answer.correct && !userResponse.answer.includes(answer)) {
              questionScore += answer.score;
            } else if (!answer.correct && userResponse.answer.includes(answer) ||
                answer.correct && !userResponse.answer.includes(answer)) {
              questionScore = 0;
            }
          });
          // If for the checkBox question was achieved a score > 0 then it is a correct response
          if (questionScore > 0) {
            correctQuestions++;
          }

          totalScore += questionScore;
        } else {
          if (userResponse.answer.correct) {
            totalScore += userResponse.answer.score;
            correctQuestions++;
          }
        }
        responseIdx++;
      })
      //Send test result data to backend
      TestResultService.saveTestResult(testId, {
        score: score,
        totalScore: totalScore,
        correctQuestions: correctQuestions
      }).then(() => {
        // Set data for Test result dialog and show it
        this.testResultData = {
          testName: this.testData.title,
          score: score,
          totalScore: totalScore,
          correctQuestions: correctQuestions,
          totalQuestions: totalQuestions
        };
        this.showTestResultDialog = true;
      });

    },
    submitUserResponses() {
      const testId = this.$route.params.id;
      const submitData = [];
      let submitDataIdx = 0;

      this.userResponses.forEach((userResponse) => {
        // Skip question if an answer was not provided
        if (userResponse.answer.length === 0) {
          return;
        }
        // CheckBox questions can have multiple selected answers
        if (userResponse.answerType === 'checkbox') {
          userResponse.answer.forEach((answer) => {
            submitData[submitDataIdx] = {
              question: {id: userResponse.questionId},
              answer: {id: answer.id}
            };
            submitDataIdx++;
          });
        } else {
          submitData[submitDataIdx] = {
            question: {id: userResponse.questionId},
            answer: {id: userResponse.answer.id}
          }
          submitDataIdx++;
        }

      })
      console.log(submitData);

      this.submitLoading = true;
      UserResponseService.saveUserResponse(testId, submitData).then(response => {
        console.log('User response saved:', response.data);
        this.submitLoading = false;
      }).catch(error => {
        this.submitLoading = false;
        console.error('Error saving user response:', error);
      });
    },
    submitTest() {
      this.submitUserResponses();
      this.submitTestResult();
    }
  },
  mounted() {
    console.log(this.$route.params);
    const testId = this.$route.params.id;

    this.$store.dispatch("test/getTestById", testId).then(data => {
      this.testData = data;
      console.log(this.testData);

      let questionIdx = 0;
      this.testData.questions.forEach(question => {
        this.userResponses[questionIdx] = {questionId: question.id, answer: [], answerType: question.answerType};
        questionIdx++;
      });

      console.log(this.userResponses);
    });

  }

};
</script>


<style scoped>
</style>
