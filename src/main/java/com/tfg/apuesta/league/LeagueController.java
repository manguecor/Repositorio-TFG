package com.tfg.apuesta.league;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
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
	
	@GetMapping("/league/{leagueId}")
	public ModelAndView showLeague(@PathVariable("leagueId") int leagueId) {
		ModelAndView mav = new ModelAndView("/welcome");
		mav.addObject(this.leagueService.findLeagueById(leagueId));
		return mav;
	}
	
	@GetMapping("/leagues")
	public ModelAndView showAllLeagues() {
		ModelAndView mav = new ModelAndView("/welcome");
		mav.addObject(this.leagueService.findAllLeagues());
		return mav;
	} 


}
