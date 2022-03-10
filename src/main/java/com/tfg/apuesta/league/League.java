package com.tfg.apuesta.league;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.tfg.apuesta.configuration.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "leagues")
public class League extends BaseEntity{
	
	@NotNull
	private String name;
	
	@NotNull
	private String code;
	
	@NotNull
	private Integer points;

	@Override
	public String toString() {
		return "League [name=" + name + ", code=" + code + ", points=" + points + "]";
	}
	
	

}
