package com.tfg.apuesta.match;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
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
	
	@GetMapping("/match/{matchId}")
	public ModelAndView showMatch(@PathVariable("matchId") int matchId) {
		ModelAndView mav = new ModelAndView("/welcome");
		mav.addObject(this.matchService.findMatchById(matchId));
		return mav;
	}
	
	@GetMapping("/matches")
	public ModelAndView showAllMatches() {
		ModelAndView mav = new ModelAndView("/welcome");
		mav.addObject(this.matchService.findAllMatches());
		return mav;
	}
	

}
