package com.tfg.apuesta.player;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {
	
	private PlayerRepository playerRepository;
	
	@Autowired
	public PlayerService(PlayerRepository playerRepository) {
		this.playerRepository = playerRepository;
	}
	
	public Player findPlayerById(Integer id) throws DataAccessException {
		return playerRepository.findPlayerById(id);
	}
	
	public List<Player> findAllPlayers() throws DataAccessException {
		return playerRepository.findAll();
	}
	
	public Optional<Player> findPlayerByUsername(String username) throws DataAccessException{
		return playerRepository.findPlayerByUsername(username);
	}
	
	public void savePlayer(Player player) throws DataAccessException {
		this.playerRepository.save(player);
	}
	
	

}
