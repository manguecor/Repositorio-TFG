package com.tfg.apuesta.bet;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.tfg.apuesta.configuration.BaseEntity;
import com.tfg.apuesta.league.League;
import com.tfg.apuesta.match.Match;
import com.tfg.apuesta.player.Player;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "bets")
public class Bet extends BaseEntity{
	
	/*@NotNull
	private Integer betPoints;
	
	@NotNull
	private BetType betType;*/
	
	private String estado;

	@ManyToOne
	@JsonBackReference(value = "player-bet")
    private Player player;
	
	@ManyToOne
	@JsonBackReference(value = "league-bet")
    private League league;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "bet")
	@JsonManagedReference(value = "bet-match")
    private Set<Match> matches;

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public League getLeague() {
		return league;
	}

	public void setLeague(League league) {
		this.league = league;
	}

	public Set<Match> getMatches() {
		return matches;
	}

	public void setMatches(Set<Match> matches) {
		this.matches = matches;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Bet() {
		super();
	}

	@Override
	public String toString() {
		return "Bet [estado=" + estado + ", player=" + player + ", league=" + league + ", matches=" + matches + "]";
	}

}
