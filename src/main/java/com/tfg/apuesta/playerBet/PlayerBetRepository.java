package com.tfg.apuesta.playerBet;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface PlayerBetRepository extends JpaRepository<PlayerBet, Integer>{
	
	@Query("SELECT playerBet FROM PlayerBet playerBet WHERE playerBet.bet.id=:id")
	List<PlayerBet> findPlayerBetByBetId(int id);

}
