package com.tfg.apuesta.client;

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
@Table(name = "clients")
public class Client extends BaseEntity{
	
	@NotNull
	private String name;
	
	@NotNull
	private String surname;
	
	@NotNull
	private String DNI;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date birthday;
	
	@NotNull
	private String email;
	
	@NotNull
	private String phone;
	
	@NotNull
	private String fav_team;

	@Override
	public String toString() {
		return "Client [name=" + name + ", surname=" + surname + ", DNI=" + DNI + ", birthday=" + birthday + ", email="
				+ email + ", phone=" + phone + ", fav_team=" + fav_team + "]";
	}
	
	

}
