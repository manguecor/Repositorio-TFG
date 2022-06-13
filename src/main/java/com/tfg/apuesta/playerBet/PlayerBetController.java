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
import com.tfg.apuesta.league.League;
import com.tfg.apuesta.match.Match;
import com.tfg.apuesta.match.MatchService;
import com.tfg.apuesta.player.Player;
import com.tfg.apuesta.player.PlayerService;
import com.tfg.apuesta.user.UserController;

@RestController
@CrossOrigin("http://localhost:8081/")
public class PlayerBetController {
	
	private PlayerBetService playerBetService;
	
	private PlayerService playerService;
	
	private BetService betService;
	
	private MatchService matchService;
	
	private UserController userController;
	
	@Autowired
	public PlayerBetController(PlayerBetService playerBetService,BetService betService,MatchService matchService,UserController userController,PlayerService playerService) {
		this.playerBetService = playerBetService;
		this.betService = betService;
		this.matchService = matchService;
		this.userController = userController;
		this.playerService = playerService;
	}
	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@PostMapping(value="/playerBets/save")
	public void savePlayerBet(@RequestBody List<String> response) {
		Integer betId = Integer.valueOf(response.get(0));
		Bet bet = this.betService.findBetById(betId);
		League league = bet.getLeague();
		List<Integer> matchesId = new ArrayList<>();
		List<String> playerResults = new ArrayList<>();
		Map<Integer,String> prueba = new HashMap<>();
		String username = this.userController.getCurrentUsername();
		List<Player> players = this.playerService.findPlayersByUsername(username);
		for(Player p: players) {
			if(league.getPlayers().contains(p)) {
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
				    playerBet.setPlayer(p);
				    this.playerBetService.save(playerBet);
				}
			}
		}	
		
	}
	
	@PostMapping(value="/playerBets/result/save")
	public void savePlayerBetResult(@RequestBody List<String> response) {
		Integer betId = Integer.valueOf(response.get(0));
		Bet bet = this.betService.findBetById(betId);
		League league = bet.getLeague();
		List<String> playerResults = new ArrayList<>();
		List<Match> matches = this.matchService.findMatchesByBetId(betId);
		String username = this.userController.getCurrentUsername();
		List<Player> players = this.playerService.findPlayersByUsername(username);
		for(Player p: players) {
			if(league.getPlayers().contains(p)) {
				List<String> awayGoals = new ArrayList<>();
				List<String> homeGoals = new ArrayList<>();
				for(int i=1;i<response.size();i++) {
					if(i%2==0) {
						awayGoals.add(response.get(i));
					} else {
						homeGoals.add(response.get(i));
					}
				}
				Integer homeGoalsResult=0;
				Integer awayGoalsResult=0;
				for(int q=0;q<homeGoals.size();q++) {
					if(homeGoals.size()==awayGoals.size()) {
						homeGoalsResult = Integer.valueOf(homeGoals.get(q));
						awayGoalsResult = Integer.valueOf(awayGoals.get(q));
						playerResults.add(homeGoalsResult + "-" + awayGoalsResult);
					}
				}
				for(int j=0;j<matches.size();j++) {
					PlayerBet playerBet = new PlayerBet();
				    playerBet.setBet(bet);
					playerBet.setPlayerResult(playerResults.get(j)); 
					playerBet.setPlayer(p);
					playerBet.setMatchId(matches.get(j).getApi_id());
					this.playerBetService.save(playerBet);
				}
			}
		}
			
		
	}
	
	@PostMapping(value="/playerBets/check/{betId}")
	public void checkPlayerBet(@PathVariable("betId") Integer betId) {
		List<PlayerBet> playerBets = this.playerBetService.findPlayerBetByBetId(betId);
		Bet bet = this.betService.findBetById(betId);
		for(int i=0;i<playerBets.size();i++) {
			PlayerBet p = playerBets.get(i);
			Player player = p.getPlayer();
			Integer matchId = p.getMatchId();
			Match match = this.matchService.getMatchWinnerById(matchId);
			if(match.getStatus().equals("FINISHED")) {
				if(match.getResult().equals(p.getPlayerResult())) {
					player.setPoints(player.getPoints()+100);
				} else {
					player.setPoints(player.getPoints()-100);
				}
				bet.setEstado("COMPROBADA");
				this.playerService.savePlayer(player);
				this.betService.save(bet);
			}
		}
	}
	
	
	@PostMapping(value="/playerBets/check/result/{betId}")
	public void checkPlayerBetResult(@PathVariable("betId") Integer betId) {
		List<PlayerBet> playerBets = this.playerBetService.findPlayerBetByBetId(betId);
		Bet bet = this.betService.findBetById(betId);
		for(int i=0;i<playerBets.size();i++) {
			PlayerBet p = playerBets.get(i);
			Player player = p.getPlayer();
			Integer matchId = p.getMatchId();
			Match match = this.matchService.getMatchResultById(matchId);
			Integer homeGoal = Integer.valueOf(match.getResult().split("-")[0]);
			Integer awayGoal = Integer.valueOf(match.getResult().split("-")[1]);
			Integer diffGoal = homeGoal-awayGoal;
			Integer homePlayerResult = Integer.valueOf(p.getPlayerResult().split("-")[0]);
			Integer awayPlayerResult = Integer.valueOf(p.getPlayerResult().split("-")[1]);
			Integer diffPlayerResult = homePlayerResult-awayPlayerResult;
			if(match.getStatus().equals("FINISHED")) {
				if(match.getResult().equals(p.getPlayerResult())) { //Resultado exacto
					player.setPoints(player.getPoints()+150);
				} else if(homeGoal==homePlayerResult) { //Goles equipos local
					player.setPoints(player.getPoints()+50);
				} else if(awayGoal==awayPlayerResult) { //Goles equipo visitante
					player.setPoints(player.getPoints()+50);
				} else if(diffGoal==diffPlayerResult) { //Diferencia de goles
					player.setPoints(player.getPoints()+75);
				} else {
					player.setPoints(player.getPoints()-100);
				}
				bet.setEstado("COMPROBADA");
				this.playerService.savePlayer(player);
				this.betService.save(bet);
			}
		}
	}
	
	@GetMapping("/playerBets/{betId}")
	public List<PlayerBet> getPlayerBetByBetID(@PathVariable("betId") int betId) {
		String username = this.userController.getCurrentUsername();
		List<Player> players = this.playerService.findPlayersByUsername(username);
		League league = this.betService.findBetById(betId).getLeague();
		List<PlayerBet> res = new ArrayList<>();
		for(Player p: players) {
			if(league.getPlayers().contains(p)) {
				List<PlayerBet> playerBets = this.playerBetService.findPlayerBetByBetId(betId);
				for(int i=0;i<playerBets.size();i++) {
					if(p.getId()==playerBets.get(i).getPlayer().getId()) {
						res.add(playerBets.get(i));
					}
				}
			}
		}
		return res;
	}
	
	@GetMapping("/playerBets/player/{playerId}")
	public List<PlayerBet> getBetsByPlayer(@PathVariable("playerId") int playerId) {
		return this.playerBetService.findPlayerBetsByPlayer(playerId);
	}
}
