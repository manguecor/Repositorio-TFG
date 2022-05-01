package com.tfg.apuesta.league;

import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.tfg.apuesta.bet.Bet;
import com.tfg.apuesta.client.Client;
import com.tfg.apuesta.configuration.BaseEntity;
import com.tfg.apuesta.player.Player;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "leagues")
public class League extends BaseEntity{
	
	//@NotNull
	private String name;
	
	//@NotNull
	private String code;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "league")
    private Set<Player> players;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "league")
    private Set<Bet> bets;

	@Override
	public String toString() {
		return "League [name=" + name + ", code=" + code + "]";
	}
	
	

}
