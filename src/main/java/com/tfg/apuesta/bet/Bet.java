package com.tfg.apuesta.bet;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.tfg.apuesta.client.Client;
import com.tfg.apuesta.configuration.BaseEntity;
import com.tfg.apuesta.league.League;
import com.tfg.apuesta.match.Match;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "bets")
public class Bet extends BaseEntity{
	
	@NotNull
	private Integer betPoints;
	
	@NotNull
	private BetType betType;

	@ManyToMany
    private Set<Client> clients;
	
	@ManyToMany
    private Set<League> leagues;
	
	@ManyToMany
    private Set<Match> matches;
	
	
	@Override
	public String toString() {
		return "Bet [betPoints=" + betPoints + ", betType=" + betType + "]";
	}
}
