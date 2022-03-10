package com.tfg.apuesta.match;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MatchRepository extends JpaRepository<Match, Integer> {
	
	@Query("SELECT DISTINCT match FROM Match match WHERE match.id=:id")
	public Match findMatchById(@Param("id") int id);

}
