import axios from "axios";

const COMPS_API_BASE_URL = 'http://localhost:8080/competitions'

class CompetitionService{
    getCompetitions(){
        return axios.get(COMPS_API_BASE_URL,{ headers: { Authorization:localStorage.getItem('token') }});
    }

    getTeamsByCompetition(competitionId){
        return axios.get(`http://localhost:8080/teams/${competitionId}`);
    }
}

export default new CompetitionService()