import axios from "axios";

class StandingService{

    getClasificationByCompetition(competitionId){
        return axios.get(`http://localhost:8080/standing/${competitionId}`);
    }
}

export default new StandingService()