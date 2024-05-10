import axios from 'axios';

const API_URL = 'http://localhost:8080/api/userGroups';

class UserGroupsService {
  createUserGroup(userGroupData) {
    return axios.post(API_URL, userGroupData);
  }

  deleteUserGroup(userGroupIdParam) {
    return axios.delete(API_URL + '?userGroupId=' + userGroupIdParam);
  }

  getAllUserGroups() {
    return axios.get(API_URL);
  }

  updateUserGroup(userGroupData) {
    return axios.put(API_URL, userGroupData);
  }

  moveUsersToGroup(userGroupId, userIds) {
    return axios.put(API_URL + '/moveUsersToGroup' + '?userGroupId=' + userGroupId, userIds);
  }
}

export default new UserGroupsService();
