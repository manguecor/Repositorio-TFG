package com.tfg.apuesta.team;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.tfg.apuesta.competition.Competition;
import com.tfg.apuesta.configuration.BaseEntity;
import com.tfg.apuesta.match.Match;
import com.tfg.apuesta.standings.Standing;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="teams")
public class Team extends BaseEntity {
	
	private String name;
	
	private String abreviation;
	
	private String initial;
	
	private String foundation_year;
	
	private String stadium;
	
	private String emblemUrl;
	
	@ManyToOne
	private Match match;
	
	@ManyToOne
	private Standing standing;
	
	@ManyToMany
    private Set<Competition> competitions;

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getAbreviation() {
		return abreviation;
	}


	public void setAbreviation(String abreviation) {
		this.abreviation = abreviation;
	}


	public String getInitial() {
		return initial;
	}


	public void setInitial(String initial) {
		this.initial = initial;
	}


	public String getFoundation_year() {
		return foundation_year;
	}


	public void setFoundation_year(String foundation_year) {
		this.foundation_year = foundation_year;
	}


	public String getStadium() {
		return stadium;
	}


	public void setStadium(String stadium) {
		this.stadium = stadium;
	}


	public Match getMatch() {
		return match;
	}


	public void setMatch(Match match) {
		this.match = match;
	}

	public Set<Competition> getCompetitions() {
		return competitions;
	}


	public void setCompetitions(Set<Competition> competitions) {
		this.competitions = competitions;
	}

	public String getEmblemUrl() {
		return emblemUrl;
	}


	public void setEmblemUrl(String emblemUrl) {
		this.emblemUrl = emblemUrl;
	}


	@Override
	public String toString() {
		return "Team [name=" + name + ", abreviation=" + abreviation + ", initial=" + initial + ", foundation_year="
				+ foundation_year + ", stadium=" + stadium + "]";
	}
}
