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
	
	@NotNull
	private String clasification;
	
	@NotNull
	private String scorers;

	@Override
	public String toString() {
		return "Competition [name=" + name + ", clasification=" + clasification + ", scorers=" + scorers + "]";
	}
	
	
	
	

}
