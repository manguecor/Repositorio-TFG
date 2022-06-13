package com.tfg.apuesta.player;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.tfg.apuesta.bet.Bet;
import com.tfg.apuesta.client.Client;
import com.tfg.apuesta.configuration.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "players")
public class Player extends BaseEntity {
	
	private Integer points;
	
	@ManyToOne(optional = true)
    private Client client;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "player")
	@JsonManagedReference(value = "player-bet")
    private Set<Bet> bets;

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

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
