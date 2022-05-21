package com.tfg.apuesta.invitation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tfg.apuesta.client.Client;
import com.tfg.apuesta.client.ClientService;
import com.tfg.apuesta.league.League;
import com.tfg.apuesta.league.LeagueService;
import com.tfg.apuesta.player.Player;
import com.tfg.apuesta.player.PlayerService;
import com.tfg.apuesta.user.UserController;

@RestController
@CrossOrigin("http://localhost:8081/")
public class InvitationController {
	
	private InvitationService invitationService;
	
	private LeagueService leagueService;
	
	private ClientService clientService;
	
	private PlayerService playerService;
	
	private UserController userController;
	
	@Autowired
	public InvitationController(InvitationService invitationService,LeagueService leagueService,ClientService clientService,PlayerService playerService,UserController userController) {
		this.invitationService = invitationService;
		this.userController = userController;
		this.leagueService = leagueService;
		this.clientService = clientService;
		this.playerService = playerService;
	}
	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@PostMapping(value="/invitation/save")
	public void saveInvitation(@RequestBody List<String> response) {
		String username = this.userController.getCurrentUsername();
		String guestClient = response.get(0).split("=")[0];
		List<Player> playersByClient = this.playerService.findPlayersByUsername(guestClient);
		Integer leagueId = Integer.valueOf(response.get(1));
		League league = this.leagueService.findLeagueById(leagueId);
		for(int i=0;i<playersByClient.size();i++) {
			if(!league.getPlayers().contains(playersByClient.get(i))) {
				Invitation invitation = new Invitation();
				invitation.setInvitingClient(username);
				invitation.setLeague(league);
				invitation.setGuestClient(guestClient);
				invitation.setInvitationState("PENDIENTE");
				this.invitationService.save(invitation);
			}
		}	
	}
	
	@GetMapping("/invitation/myInvitations")
	public List<Invitation> getInvitations(){
		String username = this.userController.getCurrentUsername();
		List<Invitation> myInvitations = new ArrayList<>();
		List<Invitation> invitations = this.invitationService.findAllInvitations();
		for(int i=0;i<invitations.size();i++) {
			String guestClient = invitations.get(i).getGuestClient();
			if(username.equals(guestClient)) {
				myInvitations.add(invitations.get(i));
			}
		}
		return myInvitations;
	}
	
	@PostMapping("/invitation/accept")
	public void acceptInvitation(@RequestBody String response) {
		String username = this.userController.getCurrentUsername();
		Optional<Client> result = this.clientService.findClientByUsername(username);
		Integer invitationId = Integer.valueOf(response.split("=")[0]);
		Invitation invitation = this.invitationService.findInvitationById(invitationId).get();
		if(result.isPresent()) {
			Client client = result.get();
			League league = invitation.getLeague();
			Player player = new Player();
			player.setPoints(500);
			player.setClient(client);
			Set<Player> players = league.getPlayers();
			if(!league.getPlayers().contains(player)) {
				this.playerService.savePlayer(player);
				players.add(player);
				this.leagueService.save(league);
				invitation.setInvitationState("ACEPTADA");
				this.invitationService.save(invitation);
			}
		}
	}
	
	@PostMapping("/invitation/decline")
	public void declineInvitation(@RequestBody String response) {
		Integer invitationId = Integer.valueOf(response.split("=")[0]);
		Invitation invitation = this.invitationService.findInvitationById(invitationId).get();
		invitation.setInvitationState("RECHAZADA");
		this.invitationService.save(invitation);
	}
	
	@DeleteMapping("/invitation/delete")
	public void deleteInvitation(@RequestBody String response) {
		Integer invitationId = Integer.valueOf(response.split("=")[0]);
		Invitation invitation = this.invitationService.findInvitationById(invitationId).get();
		this.invitationService.delete(invitation);
	}
}
