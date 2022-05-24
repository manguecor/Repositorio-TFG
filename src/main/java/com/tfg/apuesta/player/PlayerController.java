package com.tfg.apuesta.player;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import com.tfg.apuesta.league.League;
import com.tfg.apuesta.league.LeagueService;

@Controller
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
	
	@GetMapping("/player/{playerId}")
	public ModelAndView showMatch(@PathVariable("playerId") int playerId) {
		ModelAndView mav = new ModelAndView("/welcome");
		mav.addObject(this.playerService.findPlayerById(playerId));
		return mav;
	}
	
	@GetMapping("/playeres")
	public ModelAndView showAllMatches() {
		ModelAndView mav = new ModelAndView("/welcome");
		mav.addObject(this.playerService.findAllPlayers());
		return mav;
	}
}
