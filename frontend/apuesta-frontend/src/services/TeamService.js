import axios from "axios";


class TeamService{

    getTeamsByCompetition(competitionId){
        return axios.get(`http://localhost:8080/teams/${competitionId}`);
    }
}

export default new TeamService()