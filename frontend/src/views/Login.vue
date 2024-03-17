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
      <v-form ref="form" v-model="valid" @submit.prevent="handleLogin">
        <v-container>
          <v-row align="center" justify="center">
            <v-col cols="12" sm="6" md="4" align="center" justify="center">
              <p/>
              <h3>Log into your account</h3>
            </v-col>
          </v-row>
          <v-row align="center" justify="center">
            <v-col class="pb-0 pt-1" cols="12" sm="6" md="6">
              <v-text-field
                  id="email-input"
                  v-model="email"
                  label="E-mail address"
                  :rules="rules.emailRules"
                  required
                  outlined
                  dense
              ></v-text-field>
            </v-col>
          </v-row>
          <v-row align="center" justify="center">
            <v-col class="pb-0 pt-0" cols="12" sm="6" md="6">
              <v-text-field
                  id="password-input"
                  v-model="password"
                  :append-icon="show ? 'mdi-eye' : 'mdi-eye-off'"
                  :type="show ? 'text' : 'password'"
                  label="Password"
                  required
                  outlined
                  dense
                  :rules="rules.passwordRules"
                  @click:append="show = !show"
              ></v-text-field>
            </v-col>
          </v-row>
          <v-row align="center" justify="center">
            <v-col class="pb-0 pt-0" cols="12" sm="6" md="6">
              <v-btn
                  class="login-btn"
                  block
                  :loading="loading"
                  :disabled="loading"
                  type="submit"
              >
                Login
              </v-btn>
            </v-col>
          </v-row>
          <v-row align="center" justify="center">
            <v-col
                class="pb-0 pt-0"
                cols="12"
                sm="6"
                md="6"
                align="center"
                justify="center"
            >
              <div
                  id="create-account"
                  class="text-decoration-underline pt-2 link"
                  @click="goToRegisterPage()"
              >
                Create an account
              </div>
            </v-col>
          </v-row>
        </v-container>
      </v-form>
    </v-container>
  </v-app>
</template>

<script>
export default {
  name: " Login",

  data() {
    return {
      valid: false,
      loading: false,
      show: false,
      email: "",
      password: "",
      rules: {
        emailRules: [
          v => !!v || "Email is required",
          v => /.+@.+\..+/.test(v) || "Email must be valid"
        ],
        passwordRules: [v => !!v || "Password is required"]
      }
    };
  },
  methods: {
    handleLogin() {
      this.$refs.form.validate();
      if (this.valid) {
        this.loading = true;
        this.$store
            .dispatch("login", {email: this.email, password: this.password})
            .then(() => {
              this.loading = false;

              this.$router.push("/");
            })
            .catch(error => {
              console.log("Error to login!");
              console.log(error);

              // this.loginError = true;
              // this.errors.push(error);
              // this.error = true;
              this.loading = false;
            });
      }
    },
    goToRegisterPage() {
      this.$router.push("/register");
    }
  }
};
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
