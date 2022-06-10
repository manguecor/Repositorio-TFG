package com.tfg.apuesta.competition;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
	
	@Value("${api.call}")
	private String call;
	
	@Value("${api.auth}")
	private String auth;
	
	@Value("${api.key}")
	private String key;
	
	public List<Competition> showAllCompetitions() {
		String uri = call + "/competitions?plan=TIER_ONE";
	    HttpHeaders headers = new HttpHeaders();
	    headers.add(auth, key);
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
}
