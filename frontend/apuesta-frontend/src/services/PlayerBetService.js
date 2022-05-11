import axios from "axios";

const PLAYER_BET_API_BASE_URL = 'http://localhost:8080/playerBets'

class PlayerBetService{

    postPlayerBet(playerBet){
        return axios.post(PLAYER_BET_API_BASE_URL+'/save',playerBet);
    }

    checkPlayerBet(betId){
        return axios.post(PLAYER_BET_API_BASE_URL+`/check/${betId}`);
    }
}

export default new PlayerBetService()