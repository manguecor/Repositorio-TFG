package com.tfg.apuesta.bet;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.tfg.apuesta.client.Client;

@Service
public class BetService {

	private BetRepository betRepository;
	
	@Autowired
	public BetService(BetRepository betRepository) {
		this.betRepository = betRepository;
	}
	
	public void save(Bet bet) throws DataAccessException {
		this.betRepository.save(bet);
	}
}
