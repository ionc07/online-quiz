import TestGroups from "@/services/testGroups.service";


export const testGroup = {
  namespaced: true,
  actions: {
    createTestGroup({commit}, testGroupData) {
      return TestGroups.createTestGroup(testGroupData).then(
          response => {
            return Promise.resolve(response.data);
          },
          error => {
            return Promise.reject(error);
          }
      );
    },
    deleteTestGroup({commit}, testGroupId) {
      return TestGroups.deleteTestGroup(testGroupId).then(
          response => {
            return Promise.resolve(response.data);
          },
          error => {
            return Promise.reject(error);
          }
      );
    },
    getAllTestGroups({commit}) {
      return TestGroups.getAllTestGroups().then(
          response => {
            return Promise.resolve(response.data);
          },
          error => {
            return Promise.reject(error);
          }
      );
    }
  },
  mutations: {}
};
