package com.tfg.apuesta.competition;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CompetitionController {
	
	private final CompetitionService competitionService;
	
	@Autowired
	public CompetitionController(CompetitionService competitionService) {
		this.competitionService = competitionService;
	}
	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@GetMapping("/competition/{competitionId}")
	public ModelAndView showCompetition(@PathVariable("competitionId") int competitionId) {
		ModelAndView mav = new ModelAndView("/welcome");
		mav.addObject(this.competitionService.findCompetitionById(competitionId));
		return mav;
	}
	
	@GetMapping("/competitions")
	public ModelAndView showAllCompetitions() {
		ModelAndView mav = new ModelAndView("/welcome");
		mav.addObject(this.competitionService.findAllCompetitions());
		return mav;
	}

}
