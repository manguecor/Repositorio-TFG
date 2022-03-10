package com.tfg.apuesta.match;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class MatchService {
	
	private MatchRepository matchRepository;
	
	@Autowired
	public MatchService(MatchRepository matchRepository) {
		this.matchRepository = matchRepository;
	}
	
	public Match findMatchById(Integer id) throws DataAccessException {
		return matchRepository.findMatchById(id);
	}
	
	public List<Match> findAllMatches() throws DataAccessException {
		return matchRepository.findAll();
	}

}
