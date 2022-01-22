import axios from "axios";
import store from "@/store/index";
import router from "@/router";

const AXIOS = axios.create({
  baseURL: `http://localhost:8088/api/v1`,
  timeout: 5000,
});

// Add a response interceptor
AXIOS.interceptors.response.use(
    function (response) {
      if (
          response.headers["authorization"] != null &&
          localStorage.getItem("access_token") !== response.headers["authorization"]
      ) {
        localStorage.setItem("access_token", response.headers["authorization"]);
      }

      if (response.data !== null && response.data.errorType != null) {
        store.state.errorDialog = true;
        store.state.errorModel = response.data;
      }

      return response;
    },
    function (error) {
      if (error.response.status === 403) {
        store.state.errorDialog = true;
        store.state.errorModel = {
          errorType: "Access denied",
          message: "You have no permission on this action",
        };
      } else if (error.response.status === 401) {
        router.push('/login');
      }
      // Any status codes that falls outside the range of 2xx cause this function to trigger
      // Do something with response error
      return Promise.reject(error);
    }
);

// Add a request interceptor
AXIOS.interceptors.request.use(
    function (config) {
      // Do something before request is sent
      if (!config.url.includes("login") || !config.url.includes("password/reset"))
        config.headers.Authorization = localStorage.getItem("access_token");
      else delete config.headers.Authorization;

      return config;
    },
    function (error) {
      // Do something with request error
      return Promise.reject(error);
    }
);

export default {
  login(email, password) {
    return AXIOS.post(`/auth/sign-in`, {
      email: email,
      password: password,
    });
  },
  signUp(userData) {
    return AXIOS.post(`/auth/sign-up`, userData);
  },
  getUsers({page, size, sortParams}) {
    return AXIOS.get(`/users?page=${page - 1}&size=${size}${sortParams}`);
  },
  deleteUser(userId) {
    return AXIOS.delete(`/users/${userId}`);
  },

  //Dashboard endpoints
  getStatistics() {
    return AXIOS.get(`/statistics/quantitative`);
  },
  getWeeklyTestCreations() {
    return AXIOS.get(`/statistics/testCreations`);
  }
};
