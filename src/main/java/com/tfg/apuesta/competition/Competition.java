package com.tfg.apuesta.competition;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.tfg.apuesta.configuration.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "competitions")
public class Competition extends BaseEntity {
	
	@NotNull
	private String name;
	
	private String emblemUrl;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmblemUrl() {
		return emblemUrl;
	}

	public void setEmblemUrl(String emblemUrl) {
		this.emblemUrl = emblemUrl;
	}

	public Competition(@NotNull String name,String emblemUrl) {
		super();
		this.name = name;
		this.emblemUrl = emblemUrl;
	}
	
	public Competition() {
		super();
	}
	
	/*@OneToMany(cascade = CascadeType.ALL, mappedBy = "competition")
    private Set<Match> matches;
	
	@ManyToMany(mappedBy="competitions")
    private Set<Team> teams;*/	
}
