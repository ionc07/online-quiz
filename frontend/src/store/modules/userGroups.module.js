import UserGroups from "@/services/userGroups.service";


export const userGroup = {
  namespaced: true,
  actions: {
    createUserGroup({commit}, userGroupData) {
      return UserGroups.createUserGroup(userGroupData).then(
          response => {
            return Promise.resolve(response.data);
          },
          error => {
            return Promise.reject(error);
          }
      );
    },
    deleteUserGroup({commit}, userGroupId) {
      return UserGroups.deleteUserGroup(userGroupId).then(
          response => {
            return Promise.resolve(response.data);
          },
          error => {
            return Promise.reject(error);
          }
      );
    },
    getAllUserGroups({commit}) {
      return UserGroups.getAllUserGroups().then(
          response => {
            return Promise.resolve(response.data);
          },
          error => {
            return Promise.reject(error);
          }
      );
    },
    updateUserGroup({commit}, userGroupData) {
      return UserGroups.updateUserGroup(userGroupData).then(
          response => {
            return Promise.resolve(response.data);
          },
          error => {
            return Promise.reject(error);
          }
      );
    },
    moveUsersToGroup({commit}, userGroupData) {
      return UserGroups.moveUsersToGroup(userGroupData.userGroupId, userGroupData.userIds).then(
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
