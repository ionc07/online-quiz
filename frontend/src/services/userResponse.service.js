import axios from 'axios';

const API_URL = 'http://localhost:8080/api/user-responses';

class UserResponseService {
  getUserResponsesByUserIdAndTestId(userId, testId) {
    return axios.get(API_URL + '/' + userId + '?testId=' + testId);
  }

  saveUserResponse(testId, data) {
    return axios.post(API_URL + '?testId=' + testId, data);
  }

}

export default new UserResponseService();
