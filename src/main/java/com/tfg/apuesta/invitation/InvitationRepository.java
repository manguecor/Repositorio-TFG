package com.tfg.apuesta.invitation;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface InvitationRepository extends JpaRepository<Invitation, Integer>{
	
	@Query("SELECT DISTINCT invitation FROM Invitation invitation WHERE invitation.id=:id")
	Optional<Invitation> findInvitationById(int id);

}