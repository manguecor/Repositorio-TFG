package com.tfg.apuesta.invitation;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.tfg.apuesta.configuration.BaseEntity;
import com.tfg.apuesta.league.League;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "invitation")
public class Invitation extends BaseEntity {
	
	private String invitingClient;
	
	private String guestClient;
	
	private String invitationState;
	
	@OneToOne
	private League league;

	public String getInvitingClient() {
		return invitingClient;
	}

	public void setInvitingClient(String invitingClient) {
		this.invitingClient = invitingClient;
	}

	public String getGuestClient() {
		return guestClient;
	}

	public void setGuestClient(String guestClient) {
		this.guestClient = guestClient;
	}

	public String getInvitationState() {
		return invitationState;
	}

	public void setInvitationState(String invitationState) {
		this.invitationState = invitationState;
	}

	public League getLeague() {
		return league;
	}

	public void setLeague(League league) {
		this.league = league;
	}

	@Override
	public String toString() {
		return "Invitation [invitingClient=" + invitingClient + ", guestClient=" + guestClient + ", invitationState="
				+ invitationState + ", league=" + league + "]";
	}
}
