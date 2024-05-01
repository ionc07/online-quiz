import axios from 'axios';

const API_URL = 'http://localhost:8080/api/auth/';

class AuthService {
  login(user) {
    return axios
        .post(API_URL + 'sign-in', {
          email: user.email,
          password: user.password
        })
        .then(response => {
          console.log(response);
          if (response.status === 200) {
            const userRoleAndToken = response.data;
            userRoleAndToken.accessToken = 'Bearer ' + response.headers.authorization;
            localStorage.setItem('user', JSON.stringify(userRoleAndToken));
          }

          return response.data;
        });
  }

  logout() {
    localStorage.removeItem('user');
  }

  register(user) {
    return axios.post(API_URL + 'sign-up', {
      username: user.username,
      email: user.email,
      password: user.password
    });
  }
}

export default new AuthService();
