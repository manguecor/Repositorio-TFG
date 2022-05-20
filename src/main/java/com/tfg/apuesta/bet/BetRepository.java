package com.tfg.apuesta.bet;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BetRepository extends JpaRepository<Bet,Integer>{
	
	@Query("SELECT DISTINCT bet FROM Bet bet WHERE bet.id=:id")
	Bet findBetById(int id);
	
	@Query("SELECT bet FROM Bet bet WHERE bet.league.id=:id")
	List<Bet> findBetsByLeagueId(int id);

}
