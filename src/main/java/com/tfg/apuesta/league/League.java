package com.tfg.apuesta.league;

import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.tfg.apuesta.bet.Bet;
import com.tfg.apuesta.client.Client;
import com.tfg.apuesta.configuration.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "leagues")
public class League extends BaseEntity{
	
	@NotNull
	private String name;
	
	@NotNull
	private String code;
	
	
	private Integer points;
	
	@ManyToMany
    private Set<Client> clients;
	
	@ManyToMany(mappedBy="leagues")
    private Set<Bet> bets;

	@Override
	public String toString() {
		return "League [name=" + name + ", code=" + code + ", points=" + points + "]";
	}
	
	

}
