import axios from "axios";

class StandingService{

    getClasificationByCompetition(competitionId){
        return axios.get(`http://localhost:8080/standings/${competitionId}`);
    }
}

export default new StandingService()