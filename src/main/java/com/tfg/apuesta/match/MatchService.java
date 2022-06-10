package com.tfg.apuesta.match;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

@Service
public class MatchService {
	
	private MatchRepository matchRepository;
	
	@Autowired
	public MatchService(MatchRepository matchRepository) {
		this.matchRepository = matchRepository;
	}
	
	@Value("${api.call}")
	private String call;
	
	@Value("${api.auth}")
	private String auth;
	
	@Value("${api.key}")
	private String key;
	
	public List<Match> showMatchesToday(){
		Date date = new Date();  
	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
	    String today = format.format(date); 
	    String uri = call + "/matches?dateFrom="+today+"&dateTo="+today;
	    HttpHeaders headers = new HttpHeaders();
	    headers.add(auth, key);
	    HttpEntity request = new HttpEntity(headers);
	    ResponseEntity<String> response = new RestTemplate().exchange(uri, HttpMethod.GET, request, String.class);
	    String json = response.getBody();
	    JSONObject jsonObj = new JSONObject(json);
	    JSONArray arrayObject = (JSONArray) jsonObj.get("matches");
	    List<Match> res = new ArrayList<>();
	    for(int i=0;i<arrayObject.length();i++) {
	    	JSONObject object = (JSONObject) arrayObject.get(i);
	    	Match m = new Match();
	    	m.setId(Integer.valueOf(object.get("id").toString()));
	    	m.setCompetition(object.getJSONObject("competition").get("name").toString());
	    	m.setMatch_date(object.get("utcDate").toString());
	    	m.setHomeTeam(object.getJSONObject("homeTeam").get("name").toString());
	    	m.setAwayTeam(object.getJSONObject("awayTeam").get("name").toString());
	    	m.setStatus(object.get("status").toString());
	    	res.add(m);
	    }
	    return res;
	}
		
	public List<Match> showLastMatchesByTeam(Integer teamId){
	    String uri = call + "/teams/"+ teamId +"/matches?limit=5&status=FINISHED";
	    HttpHeaders headers = new HttpHeaders();
	    headers.add(auth, key);
	    HttpEntity request = new HttpEntity(headers);
	    ResponseEntity<String> response = new RestTemplate().exchange(uri, HttpMethod.GET, request, String.class);
	    String json = response.getBody();
	    JSONObject jsonObj = new JSONObject(json);
	    JSONArray arrayObject = (JSONArray) jsonObj.get("matches");
	    List<Match> res = new ArrayList<>();
	    for(int i=0;i<arrayObject.length();i++) {
	    	JSONObject object = (JSONObject) arrayObject.get(i);
	    	Match m = new Match();
	    	m.setId(Integer.valueOf(object.get("id").toString()));
	    	m.setCompetition(object.getJSONObject("competition").get("name").toString());
	    	m.setMatch_date(object.get("utcDate").toString());
	    	m.setHomeTeam(object.getJSONObject("homeTeam").get("name").toString());
	    	m.setAwayTeam(object.getJSONObject("awayTeam").get("name").toString());
	    	m.setStatus(object.get("status").toString());
	    	JSONObject arrayObject2 = (JSONObject) object.get("score");
	    	for(int j=0;j<arrayObject2.length();j++) {
	    		String homeResult = arrayObject2.getJSONObject("fullTime").get("homeTeam").toString();
		    	String awayResult = arrayObject2.getJSONObject("fullTime").get("awayTeam").toString();
		    	m.setResult(homeResult + "-" + awayResult);
	    	}
	    	res.add(m);
	    }
	    return res;
	}
	
	public List<Match> showNextMatchesByTeam(Integer teamId){
		String uri = call + "/teams/" + teamId + "/matches?status=SCHEDULED";
	    HttpHeaders headers = new HttpHeaders();
	    headers.add(auth, key);
	    HttpEntity request = new HttpEntity(headers);
	    ResponseEntity<String> response = new RestTemplate().exchange(uri, HttpMethod.GET, request, String.class);
	    String json = response.getBody();
	    JSONObject jsonObj = new JSONObject(json);
	    JSONArray arrayObject = (JSONArray) jsonObj.get("matches");
	    List<Match> res = new ArrayList<>();
	    //Set<Team> teams = new HashSet<>();
	    for(int i=0;i<arrayObject.length();i++) {
	    	JSONObject object = (JSONObject) arrayObject.get(i);
	    	Match m = new Match();
	    	m.setId(Integer.valueOf(object.get("id").toString()));
	    	m.setCompetition(object.getJSONObject("competition").get("name").toString());
	    	m.setMatch_date(object.get("utcDate").toString());
	    	m.setHomeTeam(object.getJSONObject("homeTeam").get("name").toString());
	    	m.setAwayTeam(object.getJSONObject("awayTeam").get("name").toString());
	    	m.setStatus(object.get("status").toString());
	    	res.add(m);
	    }
	    return res;
	}
	
	public Match save(Match match) throws DataAccessException {
		Match m = this.matchRepository.save(match);
		return m;
	}
	
	public List<Match> findAllMatches() throws DataAccessException {
		return this.matchRepository.findAll();
	}
	
	public List<Match> findMatchesByBetId(Integer betId) throws DataAccessException {
		return this.matchRepository.findMatchesByBetId(betId);
	}
	
	
	public Match getMatchWinnerById(Integer matchId){
		String uri = call + "/matches/" + matchId;
	    HttpHeaders headers = new HttpHeaders();
	    headers.add(auth, key);
	    HttpEntity request = new HttpEntity(headers);
	    ResponseEntity<String> response = new RestTemplate().exchange(uri, HttpMethod.GET, request, String.class);
	    String json = response.getBody();
	    JSONObject jsonObj = new JSONObject(json);
	    JSONObject json2 = (JSONObject) jsonObj.get("head2head");
	    JSONObject json3 = (JSONObject) jsonObj.get("match");
	    //JSONArray arrayObject = (JSONArray) jsonObj.get("head2head");
	    Match m = new Match();
	    for(int i=0;i<json2.length();i++) {
	    	m.setHomeTeam(json2.getJSONObject("homeTeam").get("name").toString());
	    	m.setAwayTeam(json2.getJSONObject("awayTeam").get("name").toString());
	    }
	    for(int j=0;j<json3.length();j++) {
	    	m.setResult(json3.getJSONObject("score").get("winner").toString());
	    	m.setMatch_date(json3.get("utcDate").toString());
	    	m.setStatus(json3.get("status").toString());
	    }
	    return m;
	}
	
	public Match getMatchResultById(Integer matchId){
		String uri = call + "/matches/" + matchId;
	    HttpHeaders headers = new HttpHeaders();
	    headers.add(auth, key);
	    HttpEntity request = new HttpEntity(headers);
	    ResponseEntity<String> response = new RestTemplate().exchange(uri, HttpMethod.GET, request, String.class);
	    String json = response.getBody();
	    JSONObject jsonObj = new JSONObject(json);
	    JSONObject head = (JSONObject) jsonObj.get("head2head");
	    JSONObject match = (JSONObject) jsonObj.get("match");
	    JSONObject fullTime = (JSONObject) match.getJSONObject("score").get("fullTime");
	    //JSONArray arrayObject = (JSONArray) jsonObj.get("head2head");
	    Match m = new Match();
	    //Creo que este bucle no hace falta
	    for(int i=0;i<head.length();i++) {
	    	m.setHomeTeam(head.getJSONObject("homeTeam").get("name").toString());
	    	m.setAwayTeam(head.getJSONObject("awayTeam").get("name").toString());
	    }
	    String homeTeamGoals = fullTime.get("homeTeam").toString();
	    String awayTeamGoals = fullTime.get("awayTeam").toString();
	    m.setResult(homeTeamGoals + "-" + awayTeamGoals);
	    m.setMatch_date(match.get("utcDate").toString());
	    m.setStatus(match.get("status").toString());
	    return m;
	}
	
	public List<Match> showMatchesByDate(String date){ 
	    //SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
	    //String dateFormat = format.format(date); 
	    String uri = call + "/matches?dateFrom="+date+"&dateTo="+date;
	    HttpHeaders headers = new HttpHeaders();
	    headers.add(auth, key);
	    HttpEntity request = new HttpEntity(headers);
	    ResponseEntity<String> response = new RestTemplate().exchange(uri, HttpMethod.GET, request, String.class);
	    String json = response.getBody();
	    JSONObject jsonObj = new JSONObject(json);
	    JSONArray arrayObject = (JSONArray) jsonObj.get("matches");
	    List<Match> res = new ArrayList<>();
	    for(int i=0;i<arrayObject.length();i++) {
	    	JSONObject object = (JSONObject) arrayObject.get(i);
	    	Match m = new Match();
	    	m.setId(Integer.valueOf(object.get("id").toString()));
	    	m.setCompetition(object.getJSONObject("competition").get("name").toString());
	    	m.setMatch_date(object.get("utcDate").toString());
	    	m.setHomeTeam(object.getJSONObject("homeTeam").get("name").toString());
	    	m.setAwayTeam(object.getJSONObject("awayTeam").get("name").toString());
	    	m.setStatus(object.get("status").toString());
	    	res.add(m);
	    }
	    return res;
	}

}
