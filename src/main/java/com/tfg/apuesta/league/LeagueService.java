package com.tfg.apuesta.league;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.tfg.apuesta.player.Player;

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
		return this.leagueRepository.findAllLeagues();
	}
	
	public void save(League league) throws DataAccessException {
		this.leagueRepository.save(league);
	}
	
	public void delete(League league) throws DataAccessException {
		this.leagueRepository.delete(league);
	}
	
	public Optional<League> findLeagueByCode(String code) throws DataAccessException {
		return this.leagueRepository.findLeagueByCode(code);
	}
	
	public Boolean checkPlayerInLeague(League league, Player player) {
		Boolean res = false;
		for(Player p: league.getPlayers()) {
			res = p.getClient().equals(player.getClient());
			if(res) {
				break;
			}
		}
		return res;
	}
	
	public Optional<League> findLeagueByName(String name) throws DataAccessException {
		return this.leagueRepository.findLeagueByName(name);
	}
	
	/*public List<League> findLeaguesByUsername(String username) throws DataAccessException {
		return this.leagueRepository.findLeaguesByUsername(username);
	}*/

}
