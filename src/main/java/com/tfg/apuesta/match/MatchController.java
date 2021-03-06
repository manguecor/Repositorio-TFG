package com.tfg.apuesta.match;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("http://localhost:8081/")
public class MatchController {
	
	private final MatchService matchService;
	
	@Autowired
	public MatchController(MatchService matchService) {
		this.matchService = matchService;
	}
	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setAllowedFields("id");
	}
	
	@GetMapping("/matches/today")
	public List<Match> getMatchesToday() {
		return this.matchService.showMatchesToday();
	}
	
	@GetMapping("/matches/{teamId}/results")
	public List<Match> getLastMatches(@PathVariable("teamId") int teamId) {
		return this.matchService.showLastMatchesByTeam(teamId);
	}
	
	@GetMapping("/matches/{teamId}/nextMatches")
	public List<Match> getNexMatchesByTeam(@PathVariable("teamId") int teamId) {
		return this.matchService.showNextMatchesByTeam(teamId);
	}
	
	@GetMapping("/matches/{betId}")
	public List<Match> showAllMatchesByBetId(@PathVariable("betId") int betId) {
		return this.matchService.findMatchesByBetId(betId);
	}
	
	@GetMapping("/matches/{date}/date")
	public List<Match> getMatchesByDate(@PathVariable("date") String date){
		return this.matchService.showMatchesByDate(date);
	}
	
	@GetMapping("/matches/matchAPI/{matchAPIId}")
	public Match getMatchByMatchId(@PathVariable("matchAPIId") Integer matchAPIId) {
		return this.matchService.getMatchByAPIId(matchAPIId);
	}
}
