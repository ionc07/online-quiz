<template>
  <v-container>
    <v-row>
      <v-col cols="12" md="6">
        <!-- Card for Test Name -->
        <v-card class="mb-3">
          <v-card-title>Test Name</v-card-title>
          <v-card-text>
            <v-text-field v-model="testName" label="Enter Test Name"></v-text-field>
          </v-card-text>
        </v-card>

        <v-card class="mb-3">
          <v-card-title>Create Question</v-card-title>
          <v-card-text>
            <v-form @submit.prevent="submitQuestion">
              <v-text-field v-model="newQuestion.value" label="Question"></v-text-field>

              <!-- Answer Choices -->
              <v-card v-for="(answer, index) in newQuestion.answers" :key="index" class="mb-2">
                <v-card-text>
                  <v-row>
                    <v-col>
                      <v-text-field v-model="answer.value" label="Answer Choice"></v-text-field>
                    </v-col>
                    <v-col>
                      <v-checkbox v-model="answer.correct" label="Correct"></v-checkbox>
                    </v-col>
                    <v-col>
                      <v-text-field v-model="answer.score" label="Score" type="number" small></v-text-field>
                    </v-col>
                    <v-col>
                      <v-btn icon @click="removeAnswer(index)">
                        <v-icon>mdi-delete</v-icon>
                      </v-btn>
                    </v-col>
                  </v-row>
                </v-card-text>
              </v-card>

              <!-- Add Answer Button with spacing -->
              <v-btn class="mb-3" @click="addAnswer">Add Answer Choice</v-btn>

              <!-- Submit Button -->
              <v-btn class="mb-3 ml-3" type="submit">
                {{ editingQuestionIndex === null ? 'Add Question' : 'Update Question' }}
              </v-btn>
            </v-form>
          </v-card-text>
        </v-card>
      </v-col>
      <v-col cols="12" md="6">
        <v-card>
          <v-card-title>Questions</v-card-title>
          <v-card-text>
            <draggable v-model="quiz.questions" @end="updateSequence" class="question-list" item-key="id">
              <v-list-item v-for="(question, index) in quiz.questions" :key="index" class="question-item">
                <v-list-item-content>
                  <v-list-item-title>
                    {{ index + 1 }}. {{ question.value }}
                    <span class="score"> Score: {{ question.score }}</span>
                  </v-list-item-title>
                  <v-list-item-subtitle>
      <span v-for="(answer, i) in question.answers" :key="i">
        {{ answer.value }} {{ answer.score + " ;"}}
      </span>
                  </v-list-item-subtitle>
                </v-list-item-content>
                <v-list-item-action>
                  <v-btn icon @click="editQuestion(index)">
                    <v-icon>mdi-pencil</v-icon>
                  </v-btn>
                  <v-btn icon @click="removeQuestion(index)">
                    <v-icon>mdi-delete</v-icon>
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
        description: "",
        settings: {
          availableFrom: "",
          availableTo: "",
          chatEnabled: false,
          maxAttempts: 0
        },
        questions: []
      },
      newQuestion: {
        value: "",
        answers: []
      },
      editingQuestionIndex: null
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
      console.log(this.quiz)
      this.quiz.questions.forEach((question, index) => {
        question.sequence = index;
      });
    },
    removeQuestion(index) {
      this.quiz.questions.splice(index, 1);
    },
    resetForm() {
      this.newQuestion = {
        value: "",
        answers: []
      };
      this.editingQuestionIndex = null;
    },
    goToSettings() {
      this.$router.push({name: "settings"});
    },
    addAnswer() {
      this.newQuestion.answers.push({value: "", score: 0, correct: false});
    },
    removeAnswer(index) {
      this.newQuestion.answers.splice(index, 1);
    },
  }
};
</script>
<style lang="css">
.question-item {
  background-color: rgba(9, 177, 240, 0.15);
  border-radius: 8px; /* Rounded corners */
  margin-bottom: 8px; /* Space between questions */
  padding: 16px; /* Padding for each question */
}
</style>
