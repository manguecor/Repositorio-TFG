package com.tfg.apuesta.league;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LeagueRepository extends JpaRepository<League,Integer> {
	
	@Query("SELECT DISTINCT league FROM League league WHERE league.id=:id")
	League findLeagueById(int id);

}
