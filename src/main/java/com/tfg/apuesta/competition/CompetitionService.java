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

@Service
public class CompetitionService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	private CompetitionRepository competitionRepository;
	
	@Autowired
	public CompetitionService(CompetitionRepository competitionRepository) {
		this.competitionRepository = competitionRepository;
	}
	
	public Competition findCompetitionById(Integer id) throws DataAccessException {
		return competitionRepository.findCompetitionById(id);
	}
	
	public List<Competition> findAllCompetitions() throws DataAccessException {
		return competitionRepository.findAll();
	}
	
	public List<Competition> showAllCompetitions() {
		String uri = "http://api.football-data.org/v2/competitions?plan=TIER_ONE";
		String rest = restTemplate.getForObject(uri, String.class);
		JSONObject jsonObj = new JSONObject(rest);
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
	
	public List<String> showClasificationByCompetition(Integer competitionId){
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
	    List<String> res = new ArrayList<>();
	    for(int i=0;i<arrayObject.length();i++) {
	    	JSONObject object = (JSONObject) arrayObject.get(i);
	    	JSONArray arrayObject2 = (JSONArray) object.get("table");
	    	for(int j=0;j<arrayObject2.length();j++) {
	    		JSONObject object2 = (JSONObject) arrayObject2.get(j);
		    	res.add(object2.get("position").toString());
		    	res.add(object2.getJSONObject("team").get("name").toString());
	    	}
	    }
	    return res;
	}
	
	public List<String> showScorersByCompetition(Integer competitionId){
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
	}
}
