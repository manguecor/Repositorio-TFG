package com.tfg.apuesta.player;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface PlayerRepository extends JpaRepository<Player, Integer> {
	
	@Query("select distinct player from Player player where match.id=:id")
	public Player findPlayerById(@Param("id") int id);

}
