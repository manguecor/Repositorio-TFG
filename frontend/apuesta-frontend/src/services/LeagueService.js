import axios from "axios";

const LEAGUES_API_BASE_URL = 'http://localhost:8080/leagues';

class LeagueService {
    postLeague(league) {
        return axios.post(LEAGUES_API_BASE_URL+"/save",league);
    }

    listAll() {
        return axios.get(LEAGUES_API_BASE_URL);
    }
 }

export default new LeagueService()
