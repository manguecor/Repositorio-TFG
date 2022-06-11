package com.tfg.apuesta.match;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MatchRepository extends JpaRepository<Match, Integer> {
	
	@Query("SELECT DISTINCT match FROM Match match WHERE match.id=:id")
	public Match findMatchById(@Param("id") int id);
	
	@Query("SELECT match FROM Match match WHERE bet.id=:id")
	public List<Match> findMatchesByBetId(@Param("id") int id);
	
	@Query("SELECT DISTINCT match FROM Match match WHERE match.api_id=:apiId")
	public List<Match> findMatchByAPIId(@Param("apiId") int apiId);
	

}
