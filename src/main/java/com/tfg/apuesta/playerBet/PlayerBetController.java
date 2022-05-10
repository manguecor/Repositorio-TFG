package com.tfg.apuesta.playerBet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tfg.apuesta.bet.Bet;
import com.tfg.apuesta.bet.BetService;
import com.tfg.apuesta.match.Match;
import com.tfg.apuesta.match.MatchService;
import com.tfg.apuesta.player.Player;

@RestController
@CrossOrigin("http://localhost:8081/")
public class PlayerBetController {
	
	private PlayerBetService playerBetService;
	
	private BetService betService;
	
	private MatchService matchService;
	
	@Autowired
	public PlayerBetController(PlayerBetService playerBetService,BetService betService,MatchService matchService) {
		this.playerBetService = playerBetService;
		this.betService = betService;
		this.matchService = matchService;
	}
	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@PostMapping(value="/playerBets/save")
	public void savePlayerBet(@RequestBody List<String> response) {
		Integer betId = Integer.valueOf(response.get(0));
		Bet bet = this.betService.findBetById(betId);
		List<Integer> matchesId = new ArrayList<>();
		List<String> playerResults = new ArrayList<>();
		Map<Integer,String> prueba = new HashMap<>();
		//Falta usar el getUsername para buscar el player y meterselo
		for(int i=1;i<response.size();i++) {
			if(i%2==0) {
				playerResults.add(response.get(i));
			} else {
				matchesId.add(Integer.valueOf(response.get(i)));	
			}
		}
		for(int t=0;t<matchesId.size();t++) {
			prueba.put(matchesId.get(t), playerResults.get(t));
		}
		for (Map.Entry<Integer, String> pair : prueba.entrySet()) {
			PlayerBet playerBet = new PlayerBet();
			playerBet.setBet(bet);
		    playerBet.setMatchId(pair.getKey());
		    playerBet.setPlayerResult(pair.getValue()); 
		    this.playerBetService.save(playerBet);
		}
	}
	
	@PostMapping(value="/playerBets/check/{betId}")
	public void checkPlayerBet(@PathVariable("betId") Integer betId) {
		List<PlayerBet> playerBets = this.playerBetService.findPlayerBetByBetId(betId);
		for(int i=0;i<playerBets.size();i++) {
			PlayerBet p = playerBets.get(i);
			Player player = p.getPlayer();
			Integer matchId = p.getMatchId();
			Match match = this.matchService.getMatchById(matchId);
			//String status = match.getStatus();
			this.matchService.save(match);	//No se si hay que hacer un update
			match.setStatus("FINISHED");	//Hay que conseguir que pille el status directamente
			if(match.getStatus()=="FINISHED") {
				if(match.getResult().equals(p.getPlayerResult())) {
					player.setPoints(player.getPoints()+100);
				} else {
					player.setPoints(player.getPoints()-100);
				}
			}
		}
	}


}
