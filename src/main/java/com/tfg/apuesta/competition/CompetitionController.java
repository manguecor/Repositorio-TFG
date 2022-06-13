package com.tfg.apuesta.competition;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("http://localhost:8081/")
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
	
	@GetMapping("/competitions")
	public List<Competition> getCompetitions() {
		return this.competitionService.showAllCompetitions();
	}
}
