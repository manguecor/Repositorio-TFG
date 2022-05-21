package com.tfg.apuesta.league;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LeagueRepository extends JpaRepository<League,Integer> {
	
	@Query("SELECT DISTINCT league FROM League league WHERE league.id=:id")
	League findLeagueById(int id);
	
	@Query("SELECT league FROM League league")
	List<League> findAllLeagues();
	
	/*@Query("SELECT league FROM League league WHERE league.player.client.username=:username")
	List<League> findLeaguesByUsername(String username);*/
	
	@Query("SELECT league FROM League league WHERE league.code=:code")
	Optional<League> findLeagueByCode(String code);
	
	@Query("SELECT league FROM League league WHERE league.name=:name")
	Optional<League> findLeagueByName(String name);

}
