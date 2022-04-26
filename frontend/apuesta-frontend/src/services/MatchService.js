import axios from "axios";

const MATCHES_API_BASE_URL = 'http://localhost:8080/matches'

class MatchService{
    getMatchesToday(){
        return axios.get(MATCHES_API_BASE_URL + '/today');
    }

    getLastMatches(teamId){
        return axios.get(`http://localhost:8080/matches/${teamId}/results`)
    }

    getNextMatchesByTeam(teamId){
        return axios.get(`http://localhost:8080/matches/${teamId}/nextMatches`)
    }
}

export default new MatchService()