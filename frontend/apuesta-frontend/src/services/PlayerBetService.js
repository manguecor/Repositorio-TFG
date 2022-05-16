import axios from "axios";

const PLAYER_BET_API_BASE_URL = 'http://localhost:8080/playerBets'

class PlayerBetService{

    postPlayerBet(playerBet){
        return axios.post(PLAYER_BET_API_BASE_URL+'/save',playerBet);
    }

    postPlayerBetResult(playerBet){
        return axios.post(PLAYER_BET_API_BASE_URL+'/result/save',playerBet);
    }

    checkPlayerBet(betId){
        return axios.post(PLAYER_BET_API_BASE_URL+`/check/${betId}`);
    }

    checkPlayerBetResult(betId){
        return axios.post(PLAYER_BET_API_BASE_URL+`/check/result/${betId}`);
    }
}

export default new PlayerBetService()