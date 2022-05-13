package com.tfg.apuesta.playerBet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.tfg.apuesta.bet.Bet;
import com.tfg.apuesta.configuration.BaseEntity;
import com.tfg.apuesta.player.Player;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "playerBet")
public class PlayerBet extends BaseEntity {
	
	private String playerResult;
	
	private Integer matchId;
	
	@OneToOne(cascade = CascadeType.ALL)
    private Bet bet;
	
	@OneToOne(cascade = CascadeType.ALL)
    private Player player;

	public String getPlayerResult() {
		return playerResult;
	}

	public void setPlayerResult(String playerResult) {
		this.playerResult = playerResult;
	}

	public Integer getMatchId() {
		return matchId;
	}

	public void setMatchId(Integer matchId) {
		this.matchId = matchId;
	}

	public Bet getBet() {
		return bet;
	}

	public void setBet(Bet bet) {
		this.bet = bet;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	@Override
	public String toString() {
		return "PlayerBet [playerResult=" + playerResult + ", matchId=" + matchId + ", bet="
				+ bet + ", player=" + player + "]";
	}
}
