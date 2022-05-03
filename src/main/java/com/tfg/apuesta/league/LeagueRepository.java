package com.tfg.apuesta.league;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LeagueRepository extends JpaRepository<League,Integer> {
	
	@Query("SELECT DISTINCT league FROM League league WHERE league.id=:id")
	League findLeagueById(int id);
	
	@Query("SELECT league FROM League league")
	List<League> findAllLeagues();

}
