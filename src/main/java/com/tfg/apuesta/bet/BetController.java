package com.tfg.apuesta.bet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.tfg.apuesta.client.Client;
import com.tfg.apuesta.client.ClientService;
import com.tfg.apuesta.league.League;
import com.tfg.apuesta.match.Match;
import com.tfg.apuesta.match.MatchService;
import com.tfg.apuesta.player.Player;
import com.tfg.apuesta.player.PlayerService;
import com.tfg.apuesta.user.MyUserDetails;
import com.tfg.apuesta.user.UserController;
import com.tfg.apuesta.user.UserService;

@RestController
@CrossOrigin("http://localhost:8081/")
public class BetController {
	
	private final BetService betService;
	
	private final UserService userService;
	
	private final ClientService clientService;
	
	private final PlayerService playerService;
	
	private final MatchService matchService;
	
	private final UserController userController;
	
	@Autowired
	public BetController(BetService betService,UserService userService, ClientService clientService,PlayerService playerService,MatchService matchService,UserController userController) {
		this.betService = betService;
		this.userService = userService;
		this.clientService = clientService;
		this.playerService = playerService;
		this.matchService = matchService;
		this.userController = userController;
	}
	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@PostMapping(value="/bets/save")
	public void saveBet(@RequestBody List<String> response) {
		String username = this.userController.getCurrentUsername();
		List<Integer> matchesAPIId = new ArrayList<>();
		for(int i=0;i<response.size();i++) {
			matchesAPIId.add(Integer.valueOf(response.get(i)));
		}
		Bet bet = new Bet();
		Player p = playerService.findPlayerByUsername(username).get();
		bet.setPlayer(p);
		League league = new League();
		league.setId(1);
		bet.setLeague(league);
		bet.setEstado("PENDIENTE");
		this.betService.save(bet);
		for(int j=0;j<matchesAPIId.size();j++) {
			Match m = matchService.getMatchById(matchesAPIId.get(j));
			m.setApi_id(matchesAPIId.get(j));
			m.setBets(bet);
			this.matchService.save(m);
		}	
	}
	
	@GetMapping("/bets")
	public List<Bet> showAllBets() {
		return this.betService.findAllBets();
	}
	
	@GetMapping("/bets/{betId}")
	public Bet getBetByBetID(@PathVariable("betId") int betId) {
		return this.betService.findBetById(betId);
	}
}
