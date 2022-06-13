package com.tfg.apuesta.match;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.tfg.apuesta.bet.Bet;
import com.tfg.apuesta.configuration.BaseEntity;
import com.tfg.apuesta.team.Team;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "matches")
public class Match extends BaseEntity {
	
	private String match_date;
	
	private String status;
	
	private String result;
	
	@ManyToOne
	@JsonBackReference(value = "bet-match")
    private Bet bet;
	
	private String competition;
	
	private String homeTeam;
	
	private String awayTeam;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "match")
    private Set<Team> teams;
	
	private Integer api_id;

	public String getMatch_date() {
		return match_date;
	}

	public void setMatch_date(String match_date) {
		this.match_date = match_date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Bet getBet() {
		return bet;
	}

	public void setBets(Bet bet) {
		this.bet = bet;
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

	public Integer getApi_id() {
		return api_id;
	}

	public void setApi_id(Integer api_id) {
		this.api_id = api_id;
	}

	@Override
	public String toString() {
		return "Match [match_date=" + match_date + ", status=" + status + ", result=" + result + ", bet=" + bet
				+ ", competition=" + competition + ", homeTeam=" + homeTeam + ", awayTeam=" + awayTeam + ", teams="
				+ teams + ", api_id=" + api_id + "]";
	}

	
}