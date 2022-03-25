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
import com.tfg.apuesta.user.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="clients")
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
	
	@Valid
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "username", referencedColumnName = "username")
    private User user;
	
	@ManyToMany(mappedBy="clients")
    private Set<League> leagues;
	
	@ManyToMany(mappedBy="clients")
    private Set<Bet> bets;

	@Override
	public String toString() {
		return "Client [name=" + name + ", surname=" + surname + ", DNI=" + DNI + ", birthday=" + birthday + ", email="
				+ email + ", phone=" + phone + ", fav_team=" + fav_team + "]";
	}
	
	

}
