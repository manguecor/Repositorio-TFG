package com.tfg.apuesta.league;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tfg.apuesta.bet.Bet;
import com.tfg.apuesta.client.Client;
import com.tfg.apuesta.client.ClientService;
import com.tfg.apuesta.player.Player;
import com.tfg.apuesta.player.PlayerService;
import com.tfg.apuesta.playerBet.PlayerBet;
import com.tfg.apuesta.playerBet.PlayerBetService;
import com.tfg.apuesta.user.UserController;

@RestController
@CrossOrigin("http://localhost:8081/")
public class LeagueController {
	
	private final LeagueService leagueService;
	
	private final PlayerService playerService;
	
	private final ClientService clientService;
	
	private final UserController userController;
	
	private final PlayerBetService playerBetService;
	
	@Autowired
	public LeagueController(LeagueService leagueService, PlayerService playerService, ClientService clientService, UserController userController, PlayerBetService playerBetService) {
		this.leagueService = leagueService;
		this.playerService = playerService;
		this.clientService = clientService;
		this.userController = userController;
		this.playerBetService = playerBetService;
	}
	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@GetMapping("/leagues/{leagueId}")
	public League showLeague(@PathVariable("leagueId") int leagueId) {
		return this.leagueService.findLeagueById(leagueId);
	}
	
	@GetMapping("/leagues")
	public List<League> showAllLeagues() {
		return this.leagueService.findAllLeagues();
	}
	
	@GetMapping("/leagues/myLeagues")
	public List<League> showLeaguesByUsername() {
		List<League> myLeagues = new ArrayList<>();
		String username = userController.getCurrentUsername();
		Optional<Client> result = this.clientService.findClientByUsername(username);
		if(result.isPresent()) {
			List<Player> players = this.playerService.findPlayersByUsername(result.get().getUser().getUsername());
			if(players != null) {
				List<League> leagues = this.leagueService.findAllLeagues();
				for(Player p: players) {
					for(League l: leagues) {
						if(l.getPlayers().contains(p)) {
							myLeagues.add(l);
						}
					}
				}
			}
		}
		return myLeagues;
	}
	
	@PostMapping("/leagues/save")
	public void saveLeague(@RequestBody League league) {
		String username = userController.getCurrentUsername();
		Optional<Client> result = this.clientService.findClientByUsername(username);
		if(result.isPresent()) {
			Client client = result.get();
			Player player = new Player();
			player.setPoints(500);
			player.setClient(client);
			this.playerService.savePlayer(player);
			Set<Player> players = new HashSet<>();
			players.add(player);
			league.setPlayers(players);
			this.leagueService.save(league);
		} else {
			this.leagueService.save(league);
		}
		
		
	}
	
	@DeleteMapping("/leagues/{leagueId}")
	public void deleteLeague(@PathVariable("leagueId") int leagueId) {
		String username = userController.getCurrentUsername();
		if(username.equals("admin")) {
		League league = this.leagueService.findLeagueById(leagueId);
		Set<Player> players = league.getPlayers();
		for(Player p: players) {
			Set<Bet> bets = p.getBets();
			for(Bet b: bets) {
				List<PlayerBet> playerBets = this.playerBetService.findPlayerBetByBetId(b.getId());
				for(PlayerBet pb: playerBets) {
					this.playerBetService.deletePlayerBet(pb);
				}
			}
		}
		this.leagueService.delete(league);
		}
	}
	
	@PostMapping("/leagues/join")
	public void joinLeague(@RequestBody String code) {
		String username = userController.getCurrentUsername();
		Optional<Client> result = this.clientService.findClientByUsername(username);
		if(result.isPresent()) {
			Client client = result.get();
			Optional<League> res = this.leagueService.findLeagueByCode(code.substring(0, code.length()-1));
			if(res.isPresent()) {
				League league = res.get();
				Player player = new Player();
				player.setPoints(500);
				player.setClient(client);
				Set<Player> players = league.getPlayers();
				if(!league.getPlayers().contains(player)) {
					this.playerService.savePlayer(player);
					players.add(player);
					this.leagueService.save(league);
				}
			}
		} 
	}


}
