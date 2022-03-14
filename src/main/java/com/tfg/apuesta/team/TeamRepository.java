package com.tfg.apuesta.team;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TeamRepository extends JpaRepository<Team,Integer> {
	
	@Query("SELECT DISTINCT team FROM Team team WHERE team.id=:id")
	Team findTeamById(int id);
}
