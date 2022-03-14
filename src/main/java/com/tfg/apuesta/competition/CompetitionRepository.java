package com.tfg.apuesta.competition;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CompetitionRepository extends JpaRepository<Competition,Integer> {
	
	@Query("SELECT DISTINCT competition FROM Competition competition WHERE competition.id=:id")
	Competition findCompetitionById(int id);
	
}
