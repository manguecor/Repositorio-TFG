package com.tfg.apuesta.competition;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;

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
	
	/*@GetMapping("/competition/{competitionId}")
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
	}*/
	
	@GetMapping("/competitions")
	public List<Competition> getCompetitions() {
		return this.competitionService.showAllCompetitions();
	}
	
	@GetMapping("/competition/{competitionId}")
	public List<String> getClasification(@PathVariable("competitionId") int competitionId) {
		return this.competitionService.showClasificationByCompetition(competitionId);
	}
	
	@GetMapping("/scorers/{competitionId}")
	public List<String> getScorers(@PathVariable("competitionId") int competitionId) {
		return this.competitionService.showScorersByCompetition(competitionId);
	}

}
