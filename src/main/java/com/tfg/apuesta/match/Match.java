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
	
	@NotNull
	@DateTimeFormat(pattern = "dd/MM/yyyy hh:mm")
	private Date match_date;
	
	@NotNull
	private String stadium;
	
	@NotNull
	private String result;
	
	@ManyToMany(mappedBy="matches")
    private Set<Bet> bets;
	
	@ManyToOne
	private Competition competition;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "match")
    private Set<Team> teams;

	@Override
	public String toString() {
		return "Match [match_date=" + match_date + ", stadium=" + stadium + ", result=" + result + "]";
	}
	
	
	
	

}
