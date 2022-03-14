package com.tfg.apuesta.player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PlayerController {
	
	private final PlayerService playerService;
	
	@Autowired
	public PlayerController(PlayerService playerService) {
		this.playerService = playerService;
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
