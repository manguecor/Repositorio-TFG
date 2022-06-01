package com.tfg.apuesta.player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.tfg.apuesta.league.League;
import com.tfg.apuesta.league.LeagueService;

@RestController
@CrossOrigin("http://localhost:8081/")
public class PlayerController {
	
	private final PlayerService playerService;
	
	private final LeagueService leagueService;
	
	@Autowired
	public PlayerController(PlayerService playerService,LeagueService leagueService) {
		this.playerService = playerService;
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
		//List<Player> res = new ArrayList<>();
		/*for(int i=0;i<playersList.size();i++) {
			
			res.add(playersList.get(i).getPoints());
			
			if(p.getPoints()>=playersList.get(i).getPoints()) {
				res.add(0, p);
			} else {
				res.add(p);
			}
		}*/
		Collections.sort(playersList, (o1, o2) -> o1.getPoints().compareTo(o2.getPoints()));
		Collections.reverse(playersList);
		return playersList;
	}
}
