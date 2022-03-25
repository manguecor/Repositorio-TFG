package com.tfg.apuesta.competition;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.tfg.apuesta.bet.Bet;
import com.tfg.apuesta.configuration.BaseEntity;
import com.tfg.apuesta.match.Match;
import com.tfg.apuesta.team.Team;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "competitions")
public class Competition extends BaseEntity {
	
	@NotNull
	private String name;
	
	@NotNull
	private String clasification;
	
	@NotNull
	private String scorers;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "competition")
    private Set<Match> matches;
	
	@ManyToMany(mappedBy="competitions")
    private Set<Team> teams;

	@Override
	public String toString() {
		return "Competition [name=" + name + ", clasification=" + clasification + ", scorers=" + scorers + "]";
	}
	
	
	
	

}
