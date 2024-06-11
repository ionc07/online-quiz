import UserService from "@/services/user.service";


export const user = {
  namespaced: true,
  actions: {

    getUserByEmail({commit}, email) {
      return UserService.getUserByEmail(email).then(
          response => {
            return Promise.resolve(response.data);
          },
          error => {
            return Promise.reject(error);
          }
      );
    },

    shareTestsWithUsers({commit}, data) {
      return UserService.shareTestsWithUsers(data).then(response => {
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
