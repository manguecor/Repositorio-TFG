import axios from "axios";

const SCORER_API_BASE_URL = 'http://localhost:8080/scorers'

class ScorerService{

    getScorersByCompetition(competitionId){
        return axios.get(SCORER_API_BASE_URL + `/${competitionId}`)
    }

}

export default new ScorerService()