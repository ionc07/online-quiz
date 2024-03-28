<template>
  <v-app>
    <v-container>
      <v-row align="center" justify="center" class="mt-12">
        <v-col
            cols="12"
            sm="6"
            md="4"
            align="center" justify="center"
        >
          <v-img
              max-width="20%"
              src="@/assets/quiz-logo.png"
          ></v-img>
        </v-col>
      </v-row>
      <v-form>
        <v-row align="center" justify="center">
          <v-col
              cols="12"
              sm="6"
              md="4"
              align="center" justify="center"
          >
            <p>
              <h3>Create an account</h3>
          </v-col>
        </v-row>
        <v-row align="center" justify="center">
          <v-col
              class="pb-0 pt-1"
              cols="12"
              sm="6"
              md="5"
          >
            <v-text-field
                v-model="firstName"
                :error-messages="firstNameErrors"
                label="First name"
                required
                outlined
                dense
                @input="$v.firstName.$touch()"
                @blur="$v.firstName.$touch()"
            ></v-text-field>
          </v-col>
        </v-row>
        <v-row align="center" justify="center">
          <v-col
              class="pb-0 pt-1"
              cols="12"
              sm="6"
              md="5"
          >
            <v-text-field
                v-model="lastName"
                :error-messages="lastNameErrors"
                label="Last name"
                required
                outlined
                dense
                @input="$v.lastName.$touch()"
                @blur="$v.lastName.$touch()"
            ></v-text-field>
          </v-col>
        </v-row>
        <v-row align="center" justify="center">
          <v-col
              class="pb-0 pt-1"
              cols="12"
              sm="6"
              md="5"
          >
            <v-text-field
                v-model="email"
                :error-messages="emailErrors"
                label="E-mail address"
                required
                outlined
                dense
                @input="$v.email.$touch()"
                @blur="$v.email.$touch()"
            ></v-text-field>
          </v-col>
        </v-row>
        <v-row align="center" justify="center">
          <v-col
              class="pb-0 pt-0"
              cols="12"
              sm="6"
              md="5"
          >
            <v-text-field
                v-model="password"
                :append-icon="show ? 'mdi-eye' : 'mdi-eye-off'"
                :error-messages="passwordErrors"
                :type="show ? 'text' : 'password'"
                label="Password"
                hint="At least 8 characters"
                counter
                outlined
                dense
                @input="$v.password.$touch()"
                @blur="$v.password.$touch()"
                @click:append="show = !show"
            ></v-text-field>
          </v-col>
        </v-row>
        <v-row align="center" justify="center">
          <v-col
              class="pb-0 pt-0"
              cols="12"
              sm="6"
              md="5"
          >
            <v-btn
                class="login-btn"
                block
                :loading="loading"
                :disabled="loading"
                @click="submit"
            >
              Sign up
            </v-btn>
          </v-col>
        </v-row>
        <v-row align="center" justify="center">
          <v-col
              class="pb-0 pt-0"
              cols="12"
              sm="6"
              md="5"
              align="center" justify="center"
          >
            <div class="text-decoration-underline pt-2 link"
                 @click="goToLogin()">
              Login
            </div>
          </v-col>
        </v-row>
      </v-form>
    </v-container>
  </v-app>
</template>

<script>
import {validationMixin} from 'vuelidate'
import {email, maxLength, minLength, required} from 'vuelidate/lib/validators'

export default {
  name: " Register",

  mixins: [validationMixin],
  validations: {
    firstName: {required, minLength: minLength(2), maxLength: maxLength(50)},
    lastName: {required, minLength: minLength(2), maxLength: maxLength(50)},
    email: {required, email},
    password: {required, minLength: minLength(8), maxLength: maxLength(50)},
  },

  data() {
    return {
      loading: false,
      show: false,
      email: '',
      password: '',
      firstName: '',
      lastName: ''
    }
  },
  computed: {
    firstNameErrors() {
      const errors = []
      if (!this.$v.firstName.$dirty) return errors;
      !this.$v.firstName.minLength && errors.push('Minimum 2 characters');
      !this.$v.firstName.maxLength && errors.push('Maximum 50 characters');
      !this.$v.firstName.required && errors.push('First name is required');
      return errors;
    },
    lastNameErrors() {
      const errors = []
      if (!this.$v.lastName.$dirty) return errors;
      !this.$v.lastName.minLength && errors.push('Minimum 2 characters');
      !this.$v.lastName.maxLength && errors.push('Maximum 50 characters');
      !this.$v.lastName.required && errors.push('Last name is required');
      return errors;
    },
    emailErrors() {
      const errors = []
      if (!this.$v.email.$dirty) return errors;
      !this.$v.email.email && errors.push('Must be a valid e-mail');
      !this.$v.email.required && errors.push('E-mail is required');
      return errors;
    },
    passwordErrors() {
      const errors = [];
      if (!this.$v.password.$dirty) return errors;
      !this.$v.password.minLength && errors.push('At least 8 characters')
      !this.$v.password.maxLength && errors.push('At most 50 characters')
      !this.$v.password.required && errors.push('Password is required')
      return errors;
    }
  },
  methods: {
    submit() {
      this.$v.$touch();
      console.log({
        firstName: this.firstName,
        lastName: this.lastName,
        password: this.password,
        email: this.email
      });
      if (!this.$v.$invalid) {
        this.loading = false;
        this.$store
            .dispatch("signUp", {
              firstName: this.firstName,
              lastName: this.lastName,
              password: this.password,
              email: this.email
            })
            .then(Response => {
              console.log(Response);
              if (Response.status === 201) {
                // this.$router.push("/");

              }
              this.loading = false;
            })
            .catch(error => {
              console.log(error);
              // this.registerError = true;
              // this.errors.push(error);
              this.loading = false;
            });
      }
    },
    goToLogin() {
      this.$router.push("/login");
    },
  }
}
</script>

<style scoped>
.login-btn {
  color: white !important;
  background-color: #035071 !important;
}

.login-btn.v-btn.v-btn--block.v-btn--disabled.v-btn--has-bg.v-btn--loading {
  background-color: #035071 !important;
  color: #e5e5e5 !important;
  opacity: 0.4 !important;
}

.v-form {
  padding-top: 25pt;
}

.link {
  color: #024f9d;
}

.link:hover {
  cursor: pointer;
  color: #0366c9;
}

</style>