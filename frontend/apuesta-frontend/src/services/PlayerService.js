import axios from "axios";

const PLAYERS_API_BASE_URL = 'http://localhost:8080/players';

class PlayerService {

    getPlayersByLeague(leagueId) {
        return axios.get(PLAYERS_API_BASE_URL+`/${leagueId}/points`);
    }

}    

export default new PlayerService()