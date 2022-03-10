package com.tfg.apuesta.bet;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.tfg.apuesta.configuration.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "bets")
public class Bet extends BaseEntity{
	
	@NotNull
	private Integer betPoints;
	
	@NotNull
	private BetType betType;

	@Override
	public String toString() {
		return "Bet [betPoints=" + betPoints + ", betType=" + betType + "]";
	}
}
