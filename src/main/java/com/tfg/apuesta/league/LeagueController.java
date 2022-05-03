package com.tfg.apuesta.league;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("http://localhost:8081/")
public class LeagueController {
	
	private final LeagueService leagueService;
	
	@Autowired
	public LeagueController(LeagueService leagueService) {
		this.leagueService = leagueService;
	}
	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@GetMapping("/leagues/{leagueId}")
	public League showLeague(@PathVariable("leagueId") int leagueId) {
		return this.leagueService.findLeagueById(leagueId);
	}
	
	@GetMapping("/leagues")
	public List<League> showAllLeagues() {
		return this.leagueService.findAllLeagues();
	}
	
	@PostMapping("/leagues/save")
	public League saveLeague(@RequestBody League league) {
		return this.leagueService.save(league);
	}
	
	@DeleteMapping("/leagues/{leagueId}")
	public void deleteLeague(@PathVariable("leagueId") int leagueId) {
		this.leagueService.delete(this.leagueService.findLeagueById(leagueId));
	}


}
