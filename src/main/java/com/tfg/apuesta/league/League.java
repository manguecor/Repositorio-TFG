package com.tfg.apuesta.league;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.tfg.apuesta.configuration.BaseEntity;
import com.tfg.apuesta.player.Player;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "leagues")
public class League extends BaseEntity{
	
	@NotBlank
	private String name;
	
	@NotBlank
	@Column(unique=true)
	private String code;

	@OneToMany(cascade = CascadeType.ALL)
    private Set<Player> players;
	
	/*@OneToMany(cascade = CascadeType.ALL, mappedBy = "league")
    private Set<Bet> bets;*/

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Set<Player> getPlayers() {
		return players;
	}

	public void setPlayers(Set<Player> players) {
		this.players = players;
	}

	/*public Set<Bet> getBets() {
		return bets;
	}

	public void setBets(Set<Bet> bets) {
		this.bets = bets;
	}*/

	@Override
	public String toString() {
		return "League [name=" + name + ", code=" + code + ", players=" + players + "]";
	}

	
	
	

}
