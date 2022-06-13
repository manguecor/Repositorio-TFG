package com.tfg.apuesta.player;

import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface PlayerRepository extends JpaRepository<Player, Integer> {
	
	@Query("SELECT DISTINCT player from Player player WHERE player.id=:id")
	public Player findPlayerById(@Param("id") int id);
	
	@Query("SELECT DISTINCT player FROM Player player WHERE player.client.user.username=:username")
	Optional<Player> findPlayerByUsername(String username);
	
	@Query("SELECT DISTINCT player FROM Player player WHERE player.client.user.username=:username")
	List<Player> findPlayersByUsername(String username);
	
}
