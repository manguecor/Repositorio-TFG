package com.tfg.apuesta.player;

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
@Table(name = "players")
public class Player extends BaseEntity {
	
	@NotNull
	private String name;
	
	@NotNull
	private String position;
	
	@NotNull
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date birthdate;
	
	@NotNull
	private String country;

}
