package com.tfg.apuesta.client;

import java.util.Date;
import java.util.Set;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.tfg.apuesta.bet.Bet;
import com.tfg.apuesta.configuration.BaseEntity;
import com.tfg.apuesta.league.League;
import com.tfg.apuesta.player.Player;
import com.tfg.apuesta.user.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="clients")
public class Client extends BaseEntity{
	
	//@NotNull
	private String name;
	
	//@NotNull
	private String surname;
	
	//@NotNull
	private String email;
	
	//@NotNull
	private String fav_team;
	
	//@Valid
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "username", referencedColumnName = "username")
    private User user;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFav_team() {
		return fav_team;
	}

	public void setFav_team(String fav_team) {
		this.fav_team = fav_team;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Client [name=" + name + ", surname=" + surname + ", email=" + email + ", fav_team=" + fav_team
				+ ", user=" + user + "]";
	}
	
	
}
