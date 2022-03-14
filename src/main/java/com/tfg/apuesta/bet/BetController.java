package com.tfg.apuesta.bet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BetController {
	
	private final BetService betService;
	
	@Autowired
	public BetController(BetService betService) {
		this.betService = betService;
	}
	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@GetMapping("/bet/{betId}")
	public ModelAndView showBet(@PathVariable("betId") int betId) {
		ModelAndView mav = new ModelAndView("/welcome");
		mav.addObject(this.betService.findBetById(betId));
		return mav;
	}
	
	@GetMapping("/bets")
	public ModelAndView showAllBets() {
		ModelAndView mav = new ModelAndView("/welcome");
		mav.addObject(this.betService.findAllBets());
		return mav;
	} 	

}
