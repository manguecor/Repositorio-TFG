package com.tfg.apuesta.team;

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
public class TeamService {
	
	private TeamRepository teamRepository;
	
	@Autowired
	public TeamService(TeamRepository teamRepository) {
		this.teamRepository = teamRepository;
	}
	
	public List<Team> showTeamsByCompetitions(Integer competitionId){
		String uri = "http://api.football-data.org/v2/competitions/" + competitionId + "/teams";
		String auth = "X-Auth-Token";
		String apiKey = "f3cafe6d1b40474992616dd3b183d801";
	    HttpHeaders headers = new HttpHeaders();
	    headers.add(auth, apiKey);
	    HttpEntity request = new HttpEntity(headers);
	    ResponseEntity<String> response = new RestTemplate().exchange(uri, HttpMethod.GET, request, String.class);
	    String json = response.getBody();
	    JSONObject jsonObj = new JSONObject(json);
	    JSONArray arrayObject = (JSONArray) jsonObj.get("teams");
	    List<Team> res = new ArrayList<>();
	    for(int i=0;i<arrayObject.length();i++) {
	    	JSONObject object = (JSONObject) arrayObject.get(i);
		    Team t = new Team();
		    t.setId(Integer.valueOf(object.get("id").toString()));
		    t.setName(object.get("name").toString());
		    t.setAbreviation(object.get("tla").toString());
		    t.setFoundation_year(object.get("founded").toString());
		    t.setStadium(object.get("venue").toString());
		    t.setEmblemUrl(object.get("crestUrl").toString());
		    res.add(t);
	    }
	    return res;
	}

}
