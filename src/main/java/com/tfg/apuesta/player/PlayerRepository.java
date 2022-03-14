package com.tfg.apuesta.player;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface PlayerRepository extends JpaRepository<Player, Integer> {
	
	@Query("SELECT DISTINCT player from Player player WHERE player.id=:id")
	public Player findPlayerById(@Param("id") int id);

}
