import axios from "axios";

const LEAGUES_API_BASE_URL = 'http://localhost:8080/leagues';

class LeagueService {
    postLeague(league) {
        return axios.post(LEAGUES_API_BASE_URL+"/save",league);
    }

    listAll() {
        return axios.get(LEAGUES_API_BASE_URL);
    }

    listMyLeagues() {
        return axios.get(LEAGUES_API_BASE_URL+"/myLeagues");
    }

    getLeague(leagueId) {
        return axios.get(LEAGUES_API_BASE_URL+`/${leagueId}`);
    }

    deleteLeague(leagueId) {
        return axios.delete(LEAGUES_API_BASE_URL+`/${leagueId}`);
    }

    joinLeague(code) {
        return axios.post(LEAGUES_API_BASE_URL+"/join",code);
    }
 }

export default new LeagueService()
