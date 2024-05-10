import axios from 'axios';

const API_URL = 'http://localhost:8080/api/tests';

class TestService {
  createTest(testData) {
    return axios.post(API_URL, testData);
  }

  deleteTests(testIdsParam) {
    console.log(testIdsParam);
    return axios.delete(API_URL + '?testIds=' + testIdsParam);
  }

  getTestsForCurrentUser(params) {
    return axios.get(API_URL + "/currentUser/" + params);
  }

  getSharedTestsForCurrentUser(params) {
    return axios.get(API_URL + "/currentUser/shared/" + params);
  }

  getAllTests() {
    return axios.get(API_URL);
  }
}

export default new TestService();
