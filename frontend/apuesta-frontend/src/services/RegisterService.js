import axios from "axios";

const CLIENTS_API_BASE_URL = 'http://localhost:8080/clients/save'

class RegisterService{
    
    postRegisterClient(client){
        return axios.post(CLIENTS_API_BASE_URL,client);
    }

    getClients() {
        return axios.get('http://localhost:8080/clients/usernames');
    }
}

export default new RegisterService()
