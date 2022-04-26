package com.tfg.apuesta.standings;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tfg.apuesta.team.Team;

@Service
public class StandingService {

	@Autowired
	private RestTemplate restTemplate;
	
	private StandingRepository standingRepository;
	
	@Autowired
	public StandingService(StandingRepository standingRepository) {
		this.standingRepository = standingRepository;
	}
	
	public List<Standing> showClasificationByCompetition(Integer competitionId){
		String uri = "http://api.football-data.org/v2/competitions/" + competitionId + "/standings";
		String auth = "X-Auth-Token";
		String apiKey = "f3cafe6d1b40474992616dd3b183d801";
	    HttpHeaders headers = new HttpHeaders();
	    headers.add(auth, apiKey);
	    HttpEntity request = new HttpEntity(headers);
	    ResponseEntity<String> response = new RestTemplate().exchange(uri, HttpMethod.GET, request, String.class);
	    String json = response.getBody();
		JSONObject jsonObj = new JSONObject(json);
	    JSONArray arrayObject = (JSONArray) jsonObj.get("standings");
	    List<Standing> res = new ArrayList<>();
	    for(int i=0;i<arrayObject.length();i++) {
	    	JSONObject object = (JSONObject) arrayObject.get(i);
	    	JSONArray arrayObject2 = (JSONArray) object.get("table");
	    	for(int j=0;j<arrayObject2.length();j++) {
	    		JSONObject object2 = (JSONObject) arrayObject2.get(j);
		    	Standing s = new Standing();
		    	s.setPosition(object2.get("position").toString());
		    	Set<Team> teams = new HashSet();
		    	Team t = new Team();
		    	t.setName(object2.getJSONObject("team").get("name").toString());
		    	t.setEmblemUrl(object2.getJSONObject("team").get("crestUrl").toString());
		    	teams.add(t);
		    	s.setTeams(teams);
		    	s.setPj(object2.get("playedGames").toString());
		    	s.setPg(object2.get("won").toString());
		    	s.setPe(object2.get("draw").toString());
		    	s.setPp(object2.get("lost").toString());
		    	s.setPuntos(object2.get("points").toString());
		    	res.add(s);
	    	}
	    }
	    return res;
	}
}
