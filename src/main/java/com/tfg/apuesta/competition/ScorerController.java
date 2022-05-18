package com.tfg.apuesta.competition;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.tfg.apuesta.scorers.Scorer;
import com.tfg.apuesta.scorers.ScorerService;

@RestController
@CrossOrigin("http://localhost:8081/")
public class ScorerController {
	
	private final ScorerService scorerService;
	
	@Autowired
	public ScorerController(ScorerService scorerService) {
		this.scorerService = scorerService;
	}
	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@GetMapping("/scorers/{competitionId}")
	public List<Scorer> getScorers(@PathVariable("competitionId") int competitionId) {
		return this.scorerService.showScorersByCompetition(competitionId);
	}

}
