package com.tfg.apuesta.league;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class LeagueService {
	
	private LeagueRepository leagueRepository;
	
	@Autowired
	public LeagueService(LeagueRepository leagueRepository) {
		this.leagueRepository = leagueRepository;
	}
	
	public League findLeagueById(Integer id) throws DataAccessException {
		return leagueRepository.findLeagueById(id);
	}
	
	public List<League> findAllLeagues() throws DataAccessException {
		return leagueRepository.findAll();
	}
	
	public League save(League league) throws DataAccessException {
		League l = this.leagueRepository.save(league);
		return l;
	}

}
