package com.tfg.apuesta.bet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
import com.tfg.apuesta.match.Match;
import com.tfg.apuesta.match.MatchService;
import com.tfg.apuesta.player.Player;
import com.tfg.apuesta.player.PlayerService;
import com.tfg.apuesta.user.MyUserDetails;
import com.tfg.apuesta.user.UserService;

@RestController
@CrossOrigin("http://localhost:8081/")
public class BetController {
	
	private final BetService betService;
	
	private final UserService userService;
	
	private final ClientService clientService;
	
	private final PlayerService playerService;
	
	private final MatchService matchService;
	
	@Autowired
	public BetController(BetService betService,UserService userService, ClientService clientService,PlayerService playerService,MatchService matchService) {
		this.betService = betService;
		this.userService = userService;
		this.clientService = clientService;
		this.playerService = playerService;
		this.matchService = matchService;
	}
	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@PostMapping(value="/bets/save")
	public void saveBet(@RequestBody List<String> response) {
		/*Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User currentUser = (User) authentication.getPrincipal();
		String username = currentUser.getUsername();*/
		//String username = "client1";
		String username = response.get(0);
		List<Integer> matchesAPIId = new ArrayList<>();
		for(int i=1;i<response.size();i++) {
			matchesAPIId.add(Integer.valueOf(response.get(i)));
		}
		Bet bet = new Bet();
		Player p = playerService.findPlayerByUsername(username).get();
		bet.setPlayer(p);
		bet.setLeague(p.getLeague());
		this.betService.save(bet);
		for(int j=0;j<matchesAPIId.size();j++) {
			Match m = new Match();
			m.setApi_id(matchesAPIId.get(j));
			m.setBets(bet);
			this.matchService.save(m);
		}	
		
	}
	
	
	/*@GetMapping(value="/bets/save")
	public void saveBet() {
		//Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		//User currentUser = (User) authentication.getPrincipal();
		//String username = currentUser.getUsername();
		String username = "client1";
		//Client c = clientService.findClientByUsername(username).get();
		List<Integer> matchesId = new ArrayList<>();
		matchesId.add(54);
		matchesId.add(76);
		Bet bet = new Bet();
		Player p = playerService.findPlayerByUsername(username).get();
		bet.setPlayer(p);
		bet.setLeague(p.getLeague());
		this.betService.save(bet);
		for(int i=0;i<matchesId.size();i++) {
			Match m = new Match();
			m.setApi_id(matchesId.get(i));
			m.setBets(bet);
			this.matchService.save(m);
		}	
		
	}*/
	
	

}
