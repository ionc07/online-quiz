import axios from 'axios';

const API_URL = 'http://localhost:8080/api/testGroups';

class TestGroupsService {
  createTestGroup(testGroupData) {
    return axios.post(API_URL, testGroupData);
  }

  deleteTestGroup(testGroupIdParam) {
    return axios.delete(API_URL + '?testGroupId=' + testGroupIdParam);
  }

  getAllTestGroups() {
    return axios.get(API_URL);
  }
}

export default new TestGroupsService();
