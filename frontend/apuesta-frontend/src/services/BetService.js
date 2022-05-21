import axios from "axios";

const BET_API_BASE_URL = 'http://localhost:8080/bets'
const LEAGUES_API_BASE_URL = 'http://localhost:8080/leagues'

class BetService{
    postBet(matches){
        return axios.post(BET_API_BASE_URL+'/save',matches);
    }

    getBets(){
        return axios.get(BET_API_BASE_URL);
    }

    getBetsByLeague(leagueId) {
        return axios.get(LEAGUES_API_BASE_URL+`/${leagueId}`+"/bets");
    }

    getBetByBetId(betId){
        return axios.get(BET_API_BASE_URL+`/${betId}`);
    }

    getLeagueIdByBet(betId) {
        return axios.get(BET_API_BASE_URL+`/${betId}/league`);
    }
}

export default new BetService()