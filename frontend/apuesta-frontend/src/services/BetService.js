import axios from "axios";

const BET_API_BASE_URL = 'http://localhost:8080/bets/save'

class BetService{
    postBet(matches){
        return axios.post(BET_API_BASE_URL,matches);
    }
}

export default new BetService()