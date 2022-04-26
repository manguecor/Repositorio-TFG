package com.tfg.apuesta.match;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.tfg.apuesta.bet.Bet;
import com.tfg.apuesta.competition.Competition;
import com.tfg.apuesta.configuration.BaseEntity;
import com.tfg.apuesta.team.Team;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "matches")
public class Match extends BaseEntity {
	
	//@NotNull
	//@DateTimeFormat(pattern = "dd/MM/yyyy hh:mm")
	private String match_date;
	
	//@NotNull
	/* La consulta no lo da
	private String stadium;*/
	
	//@NotNull
	private String result;
	
	@ManyToMany(mappedBy="matches")
    private Set<Bet> bets;
	
	/*@ManyToOne
	private Competition competition;*/
	
	private String competition;
	
	private String homeTeam;
	
	private String awayTeam;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "match")
    private Set<Team> teams;

	public String getMatch_date() {
		return match_date;
	}

	public void setMatch_date(String match_date) {
		this.match_date = match_date;
	}

	/*public String getStadium() {
		return stadium;
	}

	public void setStadium(String stadium) {
		this.stadium = stadium;
	}*/

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Set<Bet> getBets() {
		return bets;
	}

	public void setBets(Set<Bet> bets) {
		this.bets = bets;
	}

	public String getCompetition() {
		return competition;
	}

	public void setCompetition(String competition) {
		this.competition = competition;
	}

	public Set<Team> getTeams() {
		return teams;
	}

	public void setTeams(Set<Team> teams) {
		this.teams = teams;
	}

	public String getHomeTeam() {
		return homeTeam;
	}

	public void setHomeTeam(String homeTeam) {
		this.homeTeam = homeTeam;
	}

	public String getAwayTeam() {
		return awayTeam;
	}

	public void setAwayTeam(String awayTeam) {
		this.awayTeam = awayTeam;
	}

	@Override
	public String toString() {
		return "Match [match_date=" + match_date + ", result=" + result + "]";
	}
}
