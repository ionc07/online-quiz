import axios from 'axios';

const API_URL = 'http://localhost:8080/api/users';

class UserService {
  getUserByEmail(email) {
    return axios.get(API_URL + "/details?email=" + email);
  }

  shareTestsWithUsers(data) {
    return axios.put(API_URL + '/share/tests', data);
  }

}

export default new UserService();
