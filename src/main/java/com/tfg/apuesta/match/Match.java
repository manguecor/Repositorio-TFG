package com.tfg.apuesta.match;

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
@Table(name = "matches")
public class Match extends BaseEntity {
	
	@NotNull
	@DateTimeFormat(pattern = "dd/MM/yyyy hh:mm")
	private Date match_date;
	
	@NotNull
	private String stadium;
	
	@NotNull
	private String result;

	@Override
	public String toString() {
		return "Match [match_date=" + match_date + ", stadium=" + stadium + ", result=" + result + "]";
	}
	
	
	
	

}
