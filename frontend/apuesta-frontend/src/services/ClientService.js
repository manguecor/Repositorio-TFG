import axios from "axios";

const CLIENTS_API_BASE_URL = 'http://localhost:8080/clients'

class ClientService{
    
    getClient(){
        return axios.get(CLIENTS_API_BASE_URL + "/profile");
    }

    editClient(client){
        return axios.put(CLIENTS_API_BASE_URL + "/profile",client);
    }

    getClients() {
        return axios.get(CLIENTS_API_BASE_URL)
    }
}

export default new ClientService()