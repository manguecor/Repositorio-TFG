import axios from "axios";

const LOGIN_API_BASE_URL = 'http://localhost:8080/login'

class LoginService{
    postLogin(user){
        return axios.post(LOGIN_API_BASE_URL,user);
    }

    isUserLoggedIn() {
        let user = localStorage.getItem('token')
        if (user === null) return false
        return true
    }

    logout() {
        localStorage.removeItem('token');
        localStorage.removeItem('username');
    }

    getUser(){
        let user = localStorage.getItem('user')
        return user
    }

    getUsername(username) {
        return axios.post('http://localhost:8080/username', username);
    }
}

export default new LoginService()