package com.tfg.apuesta.player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.tfg.apuesta.league.League;
import com.tfg.apuesta.league.LeagueService;

@RestController
@CrossOrigin("http://localhost:8081/")
public class PlayerController {
	
	private final LeagueService leagueService;
	
	@Autowired
	public PlayerController(LeagueService leagueService) {
		this.leagueService = leagueService;
	}
	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setAllowedFields("id");
	}
	
	@GetMapping("/players/{leagueId}/points")
	public List<Player> getPlayersByLeague(@PathVariable("leagueId") int leagueId) {
		League league = this.leagueService.findLeagueById(leagueId);
		List<Player> playersList = new ArrayList<>();
		playersList.addAll(league.getPlayers());
		Collections.sort(playersList, (o1, o2) -> o1.getPoints().compareTo(o2.getPoints()));
		Collections.reverse(playersList);
		return playersList;
	}
}
