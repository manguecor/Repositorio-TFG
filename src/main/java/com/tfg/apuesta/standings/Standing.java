package com.tfg.apuesta.standings;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.tfg.apuesta.configuration.BaseEntity;
import com.tfg.apuesta.team.Team;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="standings")
public class Standing extends BaseEntity {
	
	private String position;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "standing")
    private Set<Team> teams;
	
	private String pj;
	
	private String pg;
	
	private String pe;
	
	private String pp;
	
	private String puntos;

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Set<Team> getTeams() {
		return teams;
	}

	public void setTeams(Set<Team> teams) {
		this.teams = teams;
	}

	public String getPj() {
		return pj;
	}

	public void setPj(String pj) {
		this.pj = pj;
	}

	public String getPg() {
		return pg;
	}

	public void setPg(String pg) {
		this.pg = pg;
	}

	public String getPe() {
		return pe;
	}

	public void setPe(String pe) {
		this.pe = pe;
	}

	public String getPp() {
		return pp;
	}

	public void setPp(String pp) {
		this.pp = pp;
	}

	public String getPuntos() {
		return puntos;
	}

	public void setPuntos(String puntos) {
		this.puntos = puntos;
	}

	@Override
	public String toString() {
		return "Standing [position=" + position + ", teams=" + teams + ", pj=" + pj + ", pg=" + pg + ", pe=" + pe
				+ ", pp=" + pp + ", puntos=" + puntos + "]";
	}
}
