<template>
  <v-container>
    <v-row>
      <v-col cols="12" md="6">
        <!-- Card for Test Name -->
        <v-card class="mb-3">
          <v-card-title>Test Name</v-card-title>
          <v-card-text>
            <v-text-field v-model="quiz.title" label="Enter Test Name"></v-text-field>
          </v-card-text>
        </v-card>

        <!-- Card for Create/Edit Question -->
        <v-card class="mb-3">
          <v-card-title>{{ editingQuestionIndex === null ? 'Create Question' : 'Edit Question' }}</v-card-title>
          <v-card-text>
            <v-form @submit.prevent="submitQuestion">
              <v-text-field v-model="newQuestion.value" label="Question description"></v-text-field>

              <!-- Select Answer Type -->
              <v-col cols="4">
                <v-select v-on:change="answerTypeChanged()" v-model="newQuestion.answerType" :items="answerTypes"
                          label="Answer Type"></v-select>
              </v-col>

              <!-- Answer Choices -->
              <v-row>
                <v-col cols="12">
                  <template v-if="newQuestion.answerType">
                    <v-row dense v-for="(answer, choiceIndex) in newQuestion.answers" :key="'choice' + choiceIndex"
                           :label="answer.value" :value="choiceIndex" :mandatory="true">
                      <v-col cols="6">
                        <v-textarea outlined auto-grow rows="2" v-model="answer.value"
                                    label="Answer choice"></v-textarea>
                      </v-col>
                      <v-col cols="auto">
                        <v-checkbox
                            v-on:click="setTheCorrectAnswer(choiceIndex, answer.correct, newQuestion.answerType)"
                            :key="choiceIndex"
                            v-model="answer.correct" label="Correct answer?"></v-checkbox>
                      </v-col>
                      <v-col cols="auto">
                        <v-btn x-large icon @click="removeAnswer(choiceIndex)">
                          <v-icon color="error">mdi-delete</v-icon>
                        </v-btn>
                      </v-col>
                    </v-row>
                  </template>
                </v-col>
              </v-row>


              <!-- Add Answer Button -->
              <v-btn color="orange"
                     v-if="!(this.newQuestion.answerType === 'truefalse' && this.newQuestion.answers.length === 1)"
                     class="mb-3" @click="addAnswer">Add Answer Choice
              </v-btn>

              <!-- Submit Button -->
              <v-btn color="primary" class="mb-3 ml-3" type="submit">
                {{ editingQuestionIndex === null ? 'Add Question' : 'Update Question' }}
              </v-btn>
            </v-form>
          </v-card-text>
        </v-card>
      </v-col>
      <v-col cols="12" md="6">
        <!-- Card for Questions -->
        <v-card>

          <v-card-title>
            <v-row>
              <v-col>
                Questions
              </v-col>
              <v-col cols="auto">
                <v-btn color="success" @click="saveTest()">
                  Save test
                  <v-icon right color="white">mdi-content-save-check</v-icon>
                </v-btn>
              </v-col>
            </v-row>
          </v-card-title>
          <v-card-text>
            <draggable v-model="quiz.questions" @end="updateSequence" class="question-list" item-key="id">
              <v-list-item v-for="(question, index) in quiz.questions" :key="index" class="question-item">
                <!-- Draggable icon -->
                <v-list-item-action>
                  <v-icon class="draggable-icon">mdi-drag</v-icon>
                </v-list-item-action>
                <v-list-item-content>
                  <v-list-item-title>
                    {{ index + 1 }}. {{ question.value }}
                    <span class="score"> Score: {{ question.score }}</span>
                  </v-list-item-title>
                </v-list-item-content>
                <v-list-item-action>
                  <v-btn icon @click="editQuestion(index)">
                    <v-icon>mdi-pencil</v-icon>
                  </v-btn>
                  <v-btn icon @click="removeQuestion(index)">
                    <v-icon color="red">mdi-delete</v-icon>
                  </v-btn>
                </v-list-item-action>
              </v-list-item>
              <v-list-item v-if="quiz.questions.length === 0">
                <v-list-item-content>No questions added</v-list-item-content>
              </v-list-item>
            </draggable>
          </v-card-text>
          <v-card-actions>
            <v-btn @click="goToSettings">Go to Settings</v-btn>
          </v-card-actions>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import draggable from 'vuedraggable';

export default {
  components: {draggable},
  data() {
    return {
      testName: '',
      quiz: {
        title: "",
        description: null,
        settings: {
          availableFrom: null,
          availableTo: null,
          chatEnabled: false,
          maxAttempts: 0
        },
        questions: []
      },
      newQuestion: {
        value: "",
        answers: [{
          value: "",
          score: 0,
          correct: false
        }],
        answerType: 'radio', // Default answer type
      },
      editingQuestionIndex: null,
      answerTypes: ['radio', 'dropdown', 'truefalse', 'checkbox']
    };
  },
  methods: {
    submitQuestion() {
      if (this.editingQuestionIndex === null) {
        this.addQuestion();
      } else {
        this.updateQuestion();
      }
    },
    addQuestion() {
      if (this.newQuestion.value) {
        this.quiz.questions.push({...this.newQuestion, sequence: this.quiz.questions.length});
        this.resetForm();
      }
    },
    updateQuestion() {
      if (this.editingQuestionIndex !== null) {
        this.$set(this.quiz.questions, this.editingQuestionIndex, {...this.newQuestion});
        this.resetForm();
      }
    },
    editQuestion(index) {
      this.newQuestion = {...this.quiz.questions[index]};
      this.editingQuestionIndex = index;
    },
    updateSequence() {
      this.quiz.questions.forEach((question, index) => {
        question.sequence = index;
      });
    },
    answerTypeChanged() {
      if (this.newQuestion.answerType === 'truefalse') {
        this.newQuestion.answers = [this.newQuestion.answers[0]];
      }
    },
    setTheCorrectAnswer(index, correct, answerType) {
      if (answerType === 'radio' || answerType === 'dropdown') {
        this.newQuestion.answers.forEach((answer) => answer.correct = false);
        if (correct) {
          this.newQuestion.answers[index].correct = true;
          console.log(`Answer with index[${index}] was set as correct.`);
        }
      }
      if (answerType === 'checkbox') {
        this.newQuestion.answers[index].correct = correct;
        console.log(`Answer with index[${index}] was set as correct[${correct}].`);
      }
    },
    removeQuestion(index) {
      this.quiz.questions.splice(index, 1);
    },
    resetForm() {
      this.newQuestion = {
        value: "",
        answers: [],
        answerType: 'radio',
        correctAnswer: null
      };
      this.editingQuestionIndex = null;
    },
    goToSettings() {
      this.$router.push({name: "settings"});
    },
    addAnswer() {
      this.newQuestion.answers.push({value: "", correct: false, answerType: this.newQuestion.answerType});
    },
    removeAnswer(index) {
      this.newQuestion.answers.splice(index, 1);
    },
    saveTest() {
      console.log()
      console.log(this.quiz);
      this.$store
          .dispatch("test/createTest", this.quiz)
          .then(Response => {
            console.log(Response);
          })
          .catch(error => {
            console.log(error);
            // this.registerError = true;
            // this.errors.push(error);
          });
    }
  }
};
</script>

<style lang="css">
.question-item {
  background-color: rgba(174, 220, 238, 0.73);
  border-radius: 8px; /* Rounded corners */
  margin-bottom: 12px; /* Space between questions */
}

.sortable-chosen {
  background-color: rgba(253, 241, 218, 0.86);
}

.draggable-icon {
  cursor: grab; /* Change cursor to indicate draggable */
}
</style>
