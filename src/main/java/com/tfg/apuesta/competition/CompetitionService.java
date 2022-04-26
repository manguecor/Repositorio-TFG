package com.tfg.apuesta.competition;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tfg.apuesta.match.Match;
import com.tfg.apuesta.team.Team;

@Service
public class CompetitionService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	private CompetitionRepository competitionRepository;
	
	@Autowired
	public CompetitionService(CompetitionRepository competitionRepository) {
		this.competitionRepository = competitionRepository;
	}
	
	public List<Competition> showAllCompetitions() {
		String uri = "http://api.football-data.org/v2/competitions?plan=TIER_ONE";
		String auth = "X-Auth-Token";
		String apiKey = "f3cafe6d1b40474992616dd3b183d801";
	    HttpHeaders headers = new HttpHeaders();
	    headers.add(auth, apiKey);
	    HttpEntity request = new HttpEntity(headers);
	    ResponseEntity<String> response = new RestTemplate().exchange(uri, HttpMethod.GET, request, String.class);
	    String json = response.getBody();
		JSONObject jsonObj = new JSONObject(json);
	    JSONArray arrayObject = (JSONArray) jsonObj.get("competitions");
	    List<Competition> res = new ArrayList<>();
	    for (int i = 0; i < arrayObject.length(); i++) {
	        JSONObject object = (JSONObject) arrayObject.get(i);
	        Competition c = new Competition();
	        c.setId(Integer.valueOf(object.get("id").toString()));
	        c.setName(object.get("name").toString());
	        c.setEmblemUrl(object.get("emblemUrl").toString());
	        res.add(c);
	    }
	    return res;
	}
	
	/*public List<String> showScorersByCompetition(Integer competitionId){
		String uri = "http://api.football-data.org/v2/competitions/" + competitionId + "/scorers";
		String auth = "X-Auth-Token";
		String apiKey = "f3cafe6d1b40474992616dd3b183d801";
	    HttpHeaders headers = new HttpHeaders();
	    headers.add(auth, apiKey);
	    HttpEntity request = new HttpEntity(headers);
	    ResponseEntity<String> response = new RestTemplate().exchange(uri, HttpMethod.GET, request, String.class);
	    String json = response.getBody();
		JSONObject jsonObj = new JSONObject(json);
	    JSONArray arrayObject = (JSONArray) jsonObj.get("scorers");
	    List<String> res = new ArrayList<>();
	    for(int i=0;i<arrayObject.length();i++) {
	    	JSONObject object = (JSONObject) arrayObject.get(i);
		    res.add(object.getJSONObject("player").get("name").toString());
		    res.add(object.getJSONObject("team").get("name").toString());
		    res.add(object.get("numberOfGoals").toString());
	    }
	    return res;
	}*/
}
