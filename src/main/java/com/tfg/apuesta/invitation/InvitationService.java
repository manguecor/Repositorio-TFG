package com.tfg.apuesta.invitation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class InvitationService {
	
	private InvitationRepository invitationRepository;
	
	@Autowired
	public InvitationService(InvitationRepository invitationRepository) {
		this.invitationRepository = invitationRepository;
	}
	
	public void save(Invitation invitation) throws DataAccessException {
		this.invitationRepository.save(invitation);
	}
	
	public List<Invitation> findAllInvitations() throws DataAccessException {
		return this.invitationRepository.findAll();
	}
	
	public Optional<Invitation> findInvitationById(Integer invitationId) throws DataAccessException {
		return this.invitationRepository.findInvitationById(invitationId);
	}
	
	public void delete(Invitation invitation) throws DataAccessException {
		this.invitationRepository.delete(invitation);
	}

}
