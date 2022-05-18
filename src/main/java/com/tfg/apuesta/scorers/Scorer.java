package com.tfg.apuesta.scorers;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.tfg.apuesta.configuration.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="scorers")
public class Scorer extends BaseEntity {
	
	private String footballPlayer;
	
	private String playerTeam;
	
	private String goals;

	public String getFootballPlayer() {
		return footballPlayer;
	}

	public void setFootballPlayer(String footballPlayer) {
		this.footballPlayer = footballPlayer;
	}

	public String getPlayerTeam() {
		return playerTeam;
	}

	public void setPlayerTeam(String playerTeam) {
		this.playerTeam = playerTeam;
	}

	public String getGoals() {
		return goals;
	}

	public void setGoals(String goals) {
		this.goals = goals;
	}

	@Override
	public String toString() {
		return "Scorer [footballPlayer=" + footballPlayer + ", playerTeam=" + playerTeam + ", goals=" + goals + "]";
	}
}
