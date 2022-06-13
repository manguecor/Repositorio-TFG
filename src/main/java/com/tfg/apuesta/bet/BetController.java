package com.tfg.apuesta.bet;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tfg.apuesta.league.League;
import com.tfg.apuesta.league.LeagueService;
import com.tfg.apuesta.match.Match;
import com.tfg.apuesta.match.MatchService;
import com.tfg.apuesta.player.Player;
import com.tfg.apuesta.player.PlayerService;
import com.tfg.apuesta.user.UserController;

@RestController
@CrossOrigin("http://localhost:8081/")
public class BetController {
	
	private final BetService betService;
	
	private final LeagueService leagueService;
	
	private final PlayerService playerService;
	
	private final MatchService matchService;
	
	private final UserController userController;
	
	@Autowired
	public BetController(BetService betService,PlayerService playerService,MatchService matchService,
			UserController userController, LeagueService leagueService) {
		this.betService = betService;
		this.playerService = playerService;
		this.matchService = matchService;
		this.userController = userController;
		this.leagueService = leagueService;
	}
	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@PostMapping(value="/bets/save")
	public void saveMatchBet(@RequestBody List<String> response) {
		System.out.println(response.get(2));
		Optional<League> result = this.leagueService.findLeagueByName(response.get(2));
		if(result.isPresent()) {
			String username = this.userController.getCurrentUsername();
			List<Integer> matchesAPIId = new ArrayList<>();
			for(int i=3;i<response.size();i++) {
				matchesAPIId.add(Integer.valueOf(response.get(i)));
			}
			Bet bet = new Bet();
			String betType = response.get(0);
			if(betType.equals("WINNER")) {
				bet.setBetType(BetType.WINNER);
			} else if(betType.equals("RESULT")) {
				bet.setBetType(BetType.RESULT);	
			}
			List<Player> players = playerService.findPlayersByUsername(username);
			for(Player p: players) {
				if(result.get().getPlayers().contains(p)) {
					bet.setPlayer(p);
					bet.setDescription(response.get(1));
					bet.setLeague(result.get());
					bet.setEstado("PENDIENTE");
					this.betService.save(bet);
					for(int j=0;j<matchesAPIId.size();j++) {
						Match m = matchService.getMatchWinnerById(matchesAPIId.get(j));
						m.setApi_id(matchesAPIId.get(j));
						m.setBets(bet);
						this.matchService.save(m);
					}
				}
			}	
		}	
	}
	
	@GetMapping("/bets")
	public List<Bet> showAllBets() {
		return this.betService.findAllBets();
	}
	
	@GetMapping("/leagues/{leagueId}/bets")
	public List<Bet> showBetsByLeague(@PathVariable("leagueId") int leagueId) {
		return this.betService.findBetsByLeague(leagueId);
	}
	
	@GetMapping("/bets/{betId}")
	public Bet getBetByBetID(@PathVariable("betId") int betId) {
		return this.betService.findBetById(betId);
	}
	
	@GetMapping("/bets/{betId}/league")
	public Integer getLeagueIdByBet(@PathVariable("betId") int betId) {
		return this.betService.findBetById(betId).getLeague().getId();
	}
	
	@PostMapping("/bets/close")
	public void closeBet(@RequestBody String response) {
		Integer betId = Integer.valueOf(response.split("=")[0]);
		Bet bet = this.betService.findBetById(betId);
		bet.setEstado("CERRADA");
		this.betService.save(bet);
	}
}
