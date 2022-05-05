package com.tfg.apuesta.player;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.tfg.apuesta.bet.Bet;
import com.tfg.apuesta.client.Client;
import com.tfg.apuesta.configuration.BaseEntity;
import com.tfg.apuesta.league.League;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "players")
public class Player extends BaseEntity {
	
	//@NotNull
	private Integer points;
	
	/*@ManyToOne
    private League league;*/
	
	@ManyToOne(optional = true, cascade = CascadeType.ALL)
    private Client client;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "player")
    private Set<Bet> bets;

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	/*public League getLeague() {
		return league;
	}

	public void setLeague(League league) {
		this.league = league;
	}*/
	
	
	

	public Set<Bet> getBets() {
		return bets;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public void setBets(Set<Bet> bets) {
		this.bets = bets;
	}
	

}
