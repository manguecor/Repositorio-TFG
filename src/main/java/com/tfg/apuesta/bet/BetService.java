package com.tfg.apuesta.bet;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class BetService {

	private BetRepository betRepository;
	
	@Autowired
	public BetService(BetRepository betRepository) {
		this.betRepository = betRepository;
	}
	
	public Bet findBetById(Integer id) throws DataAccessException {
		return betRepository.findBetById(id);
	}
	
	public List<Bet> findAllBets() throws DataAccessException {
		return betRepository.findAll();
	}
}
