package com.tfg.apuesta.team;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TeamController {
	
	private final TeamService teamService;
	
	@Autowired
	public TeamController(TeamService teamService) {
		this.teamService = teamService;
	}
	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@GetMapping("/team/{teamId}")
	public ModelAndView showTeam(@PathVariable("teamId") int teamId) {
		ModelAndView mav = new ModelAndView("/welcome");
		mav.addObject(this.teamService.findTeamById(teamId));
		return mav;
	}
	
	@GetMapping("/teams")
	public ModelAndView showAllTeams() {
		ModelAndView mav = new ModelAndView("/welcome");
		mav.addObject(this.teamService.findAllTeams());
		return mav;
	} 


}
