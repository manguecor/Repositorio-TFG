import axios from "axios";

const COMPS_API_BASE_URL = 'http://localhost:8080/competitions'

class CompetitionService{
    getCompetitions(){
        return axios.get(COMPS_API_BASE_URL);
    }

    getTeamsByCompetition(competitionId){
        return axios.get(`http://localhost:8080/teams/${competitionId}`);
    }

    getNextMatchesByTeam(teamId){
        return axios.get(`http://localhost:8080/teams/${teamId}/nextMatches`)
    }
}

export default new CompetitionService()