import TestService from "@/services/test.service";

const user = JSON.parse(localStorage.getItem('user'));

export const test = {
  namespaced: true,
  actions: {
    createTest({commit}, testData) {
      return TestService.createTest(testData).then(
          response => {
            commit('testCreationSuccess');
            return Promise.resolve(response.data);
          },
          error => {
            commit('testCreationFailure');
            return Promise.reject(error);
          }
      );
    },
    getTestsForCurrentUser({commit}, params) {
      return TestService.getTestsForCurrentUser(params).then(
          response => {
            return Promise.resolve(response.data);
          },
          error => {
            return Promise.reject(error);
          }
      );
    },
    getAllTests({commit}) {
      return TestService.getTests().then(
          response => {
            return Promise.resolve(response.data);
          },
          error => {
            return Promise.reject(error);
          }
      );
    }
  },
  mutations: {
    testCreationSuccess(state) {
    },
    testCreationFailure(state) {
    }
  }
};
