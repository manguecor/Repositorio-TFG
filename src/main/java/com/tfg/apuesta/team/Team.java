package com.tfg.apuesta.team;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.tfg.apuesta.competition.Competition;
import com.tfg.apuesta.configuration.BaseEntity;
import com.tfg.apuesta.match.Match;
import com.tfg.apuesta.player.Player;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="teams")
public class Team extends BaseEntity {
	
	@NotNull
	private String name;
	
	@NotNull
	private String abreviation;
	
	@NotNull
	private String initial;
	
	@NotNull
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date foundation_date;
	
	@NotNull
	private String stadium;
	
	@ManyToOne
	private Match match;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "team")
    private Set<Player> players;
	
	@ManyToMany
    private Set<Competition> competitions;

	@Override
	public String toString() {
		return "Team [name=" + name + ", abreviation=" + abreviation + ", initial=" + initial + ", foundation_date="
				+ foundation_date + ", stadium=" + stadium + "]";
	}
	
	

}
