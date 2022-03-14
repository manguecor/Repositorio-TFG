package com.tfg.apuesta.competition;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CompetitionService {

	private CompetitionRepository competitionRepository;
	
	@Autowired
	public CompetitionService(CompetitionRepository competitionRepository) {
		this.competitionRepository = competitionRepository;
	}
	
	public Competition findCompetitionById(Integer id) throws DataAccessException {
		return competitionRepository.findCompetitionById(id);
	}
	
	public List<Competition> findAllCompetitions() throws DataAccessException {
		return competitionRepository.findAll();
	}
}
