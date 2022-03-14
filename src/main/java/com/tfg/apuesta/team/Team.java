package com.tfg.apuesta.team;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.tfg.apuesta.configuration.BaseEntity;

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
	private String abbreviation;
	
	@NotNull
	private String initial;
	
	@NotNull
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date foundation_date;
	
	@NotNull
	private String stadium;

	@Override
	public String toString() {
		return "Team [name=" + name + ", abbreviation=" + abbreviation + ", initial=" + initial + ", foundation_date="
				+ foundation_date + ", stadium=" + stadium + "]";
	}
	
	

}
