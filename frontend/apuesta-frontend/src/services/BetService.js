import axios from "axios";

const BET_API_BASE_URL = 'http://localhost:8080/bets'

class BetService{
    postBet(matches){
        return axios.post(BET_API_BASE_URL+'/save',matches);
    }

    getBets(){
        return axios.get(BET_API_BASE_URL);
    }

    postPlayerBet(playerBet){
        return axios.post(BET_API_BASE_URL+'/playerBet',playerBet);
    }
}

export default new BetService()