import axios from 'axios';

const API_URL = 'http://localhost:8080/api/test-results';

class TestResultService {
  getTestResults(params) {
    return axios.get(API_URL + '/user-tests' + params);
  }

  saveTestResult(testId, data) {
    return axios.post(API_URL + '?testId=' + testId, data);
  }

  deleteTestResults(testResultIdsParam) {
    return axios.delete(API_URL + '?testResultIds=' + testResultIdsParam);
  }


}

export default new TestResultService();
