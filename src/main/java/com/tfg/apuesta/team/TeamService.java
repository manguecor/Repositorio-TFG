package com.tfg.apuesta.team;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class TeamService {
	
	private TeamRepository teamRepository;
	
	@Autowired
	public TeamService(TeamRepository teamRepository) {
		this.teamRepository = teamRepository;
	}
	
	public Team findTeamById(Integer id) throws DataAccessException {
		return teamRepository.findTeamById(id);
	}
	
	public List<Team> findAllTeams() throws DataAccessException {
		return teamRepository.findAll();
	}

}
