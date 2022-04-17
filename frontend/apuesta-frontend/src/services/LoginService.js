import axios from "axios";

const LOGIN_API_BASE_URL = 'http://localhost:8080/login'

class LoginService{
    postLogin(user){
        return axios.post(LOGIN_API_BASE_URL,user);
    }
}

export default new LoginService()