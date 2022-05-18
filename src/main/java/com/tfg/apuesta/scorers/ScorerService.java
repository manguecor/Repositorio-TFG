package com.tfg.apuesta.scorers;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ScorerService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	private ScorerRepository scorerRepository;
	
	@Autowired
	public ScorerService(ScorerRepository scorerRepository) {
		this.scorerRepository = scorerRepository;
	}
	
	public List<Scorer> showScorersByCompetition(Integer competitionId){
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
	    List<Scorer> res = new ArrayList<>();
	    for(int i=0;i<arrayObject.length();i++) {
	    	JSONObject object = (JSONObject) arrayObject.get(i);
	    	Scorer scorer = new Scorer();
	    	scorer.setFootballPlayer(object.getJSONObject("player").get("name").toString());
	    	scorer.setPlayerTeam(object.getJSONObject("team").get("name").toString());
	    	scorer.setGoals(object.get("numberOfGoals").toString());
		    res.add(scorer);
	    }
	    return res;
	}

}
