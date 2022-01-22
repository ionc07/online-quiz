import Vue from "vue";
import Vuex from "vuex";
import api from "../components/backend-api";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    admin: true,
    auth: {},
    app: {
      activeRoute: "", fetchLoading: false,
      auth: {loggedIn: true}
    },
    data: {
      dashboard: {
        quantitativeStatistics: {}
      }
    }
  },
  mutations: {
    login_success(state, payload) {
      state.app.auth.loggedIn = true;

      this.state.admin = (payload === 'ROLE_admin');

      this.state.role = payload;
      localStorage.setItem("role", payload);
    },
    login_error(state, payload) {
      state.app.auth.loggedIn = false;
      console.log(payload);
      // state.login.loginError = true;
      // state.login.error = payload.message;
      // setTimeout(() => {
      //   state.login.error = false;
      //   state.login.loginError = false;
      // }, 3000)
    },
    signUp_success() {

    },
    logoutUser(state) {
      state.app.auth.loggedIn = false;
    },
    getStatisticsSuccess(state, payload) {
      state.data.dashboard.quantitativeStatistics = payload;
    }
  },
  actions: {
    login({commit}, {email, password}) {
      return new Promise((resolve, reject) => {
        api
            .login(email, password)
            .then(response => {
              console.log(response);
              if (response.status === 200) {
                commit("login_success", response.data.role);

              }
              resolve(response);
            })
            .catch(() => {
              commit("login_error", {
                message: "Bad credentials"
              });
              reject("Bad credentials!");
            });
      });
    },
    signUp({commit}, user) {
      return new Promise(() => {
        api
            .signUp(user)
            .then(response => {
              console.log(response);
              if (response.status === 201 && response.data.errorType == null) {
                commit("signUp_success");
              }
              // else {
              //   commit("register_error", {
              //     message: response.data.message
              //   });
              // }
              // resolve(response);
            })
            .catch(() => {
              // commit("register_error", {
              //   message: "Something went wrong...",
              // });
              // reject("Something went wrong...");
            });
      });
    },
    getStatistics({commit}) {
      return new Promise((resolve) => {
        api
            .getStatistics()
            .then(response => {
              console.log(response);
              commit("getStatisticsSuccess", response.data);
              // else {
              //   commit("register_error", {
              //     message: response.data.message
              //   });
              // }
              resolve(response);
            })
            .catch(() => {
              // commit("register_error", {
              //   message: "Something went wrong...",
              // });
              // reject("Something went wrong...");
            });
      });
    }
  },
  getters: {
    getStatisticsData: state => state.data.dashboard.quantitativeStatistics,
  },
  modules: {},
});
