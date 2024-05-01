import axios from 'axios';

const API_URL = 'http://localhost:8080/api/tests/';

class TestService {
  createTest(testData) {
    return axios.post(API_URL, testData);
  }

  getTestsForCurrentUser(params) {
    return axios.get(API_URL + "currentUser/" + params);
  }

  getAllTests() {
    return axios.get(API_URL);
  }
}

export default new TestService();
