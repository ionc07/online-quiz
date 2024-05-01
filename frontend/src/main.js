import Vue from "vue";
import App from "./App.vue";
import "./registerServiceWorker";
import router from "./router";
import store from "./store";
import vuetify from "./plugins/vuetify";
import axios from "axios";

Vue.config.productionTip = false;


// Add a request interceptor
axios.interceptors.request.use(function (config) {
  // Do something before request is sent
  const currentUser = JSON.parse(localStorage.getItem('user'));
  console.log(currentUser);
  console.log('Axios interceptor HIT');
  if (currentUser && currentUser.accessToken) {
    console.log('Set Authorization header');
    config.headers['Authorization'] = currentUser.accessToken;
  }

  return config;
}, function (error) {
  // Do something with request error
  return Promise.reject(error);
});

// Add a response interceptor
axios.interceptors.response.use(function (response) {
  // Any status code that lie within the range of 2xx cause this function to trigger
  // Do something with response data
  return response;
}, function (error) {
  // Any status codes that falls outside the range of 2xx cause this function to trigger
  // Do something with response error
  console.log(error)
  return Promise.reject(error);
});

new Vue({
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount("#app");
