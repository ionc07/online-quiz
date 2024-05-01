<template>
  <v-container>
    <v-row>
      <v-col cols="12">
        <v-text-field v-model="email" label="Email" outlined></v-text-field>
        <v-btn color="primary" @click="addUserToGroup">Add User</v-btn>
        <v-snackbar v-model="snackbar" :timeout="snackbarTimeout" multi-line>{{ snackbarText }}</v-snackbar>
      </v-col>
    </v-row>
  </v-container>

</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      email: '',
      snackbar: false,
      snackbarText: '',
      snackbarTimeout: 3000,
    };
  },
  methods: {
    addUserToGroup() {
      // Check if email is empty
      if (!this.email) {
        this.showSnackbar('Email is required');
        return;
      }

      // Make API call to check if email exists
      axios.post('/api/checkEmail', { email: this.email })
          .then(response => {
            // If email exists, add user to group
            // You can replace this with your actual API call to add user to group
            this.addUserToGroupInDatabase();
          })
          .catch(error => {
            // If email does not exist, show error message
            if (error.response && error.response.status === 404) {
              this.showSnackbar('Email does not exist');
            } else {
              this.showSnackbar('Error checking email');
            }
          });
    },
    addUserToGroupInDatabase() {
      // Your API call to add user to group in the database
      // Replace this with your actual API call
      axios.post('/api/addUserToGroup', { email: this.email })
          .then(response => {
            this.showSnackbar('User added successfully');
          })
          .catch(error => {
            this.showSnackbar('Error adding user');
          });
    },
    showSnackbar(message) {
      this.snackbarText = message;
      this.snackbar = true;
    },
  },
};
</script>

<style scoped>

</style>