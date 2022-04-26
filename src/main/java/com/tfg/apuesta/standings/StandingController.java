package com.tfg.apuesta.standings;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin("http://localhost:8081/")
public class StandingController {
	
private final StandingService standingService;
	
	@Autowired
	public StandingController(StandingService standingService) {
		this.standingService = standingService;
	}
	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@GetMapping("/standings/{competitionId}")
	public List<Standing> getClasification(@PathVariable("competitionId") int competitionId) {
		return this.standingService.showClasificationByCompetition(competitionId);
	}

}
