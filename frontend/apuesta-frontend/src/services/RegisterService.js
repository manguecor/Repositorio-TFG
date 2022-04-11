import axios from "axios";

const USERS_API_BASE_URL = 'http://localhost:8080/users/save'
const CLIENTS_API_BASE_URL = 'http://localhost:8080/clients/save'

class RegisterService{
    postRegisterUser(user){
        return axios.post(USERS_API_BASE_URL,user);
    }

    postRegisterClient(client){
        return axios.post(CLIENTS_API_BASE_URL,client);
    }
}

export default new RegisterService()