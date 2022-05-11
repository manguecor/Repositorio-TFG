package com.tfg.apuesta.playerBet;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;


@Service
public class PlayerBetService {
	
	private PlayerBetRepository playerBetRepository;
	
	@Autowired
	public PlayerBetService(PlayerBetRepository playerBetRepository) {
		this.playerBetRepository = playerBetRepository;
	}
	
	public void save(PlayerBet playerBet) throws DataAccessException {
		this.playerBetRepository.save(playerBet);
	}
	
	public List<PlayerBet> findPlayerBetByBetId(Integer betId) throws DataAccessException {
		return this.playerBetRepository.findPlayerBetByBetId(betId);
	}

}
