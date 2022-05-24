package com.tfg.apuesta.client;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tfg.apuesta.user.AuthoritiesService;
import com.tfg.apuesta.user.User;
import com.tfg.apuesta.user.UserController;
import com.tfg.apuesta.user.UserService;

@RestController
@CrossOrigin("http://localhost:8081/")
public class ClientController {
	
	private final ClientService clientService;
	
	private final UserService userService;
	
	private final AuthoritiesService authoritiesService;
	
	private final UserController userController;
	
	@Autowired
	private ClientRepository repository;
	
	@Autowired
	public ClientController(ClientService clientService, UserService userService, AuthoritiesService authoritiesService,
			UserController userController) {
		this.clientService = clientService;
		this.userService = userService;
		this.authoritiesService = authoritiesService;
		this.userController = userController;
	}
	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@GetMapping("/clients/{clientId}")
	public Client showClient(@PathVariable("clientId") int clientId) {
		return repository.findClientById(clientId);
	}
	
	@GetMapping("/clients/{username}")
	public Optional<Client> findClientByUsername(@PathVariable("username") String username) {
		return repository.findClientByUsername(username);
	}
	
	@GetMapping("/clients")
	public List<Client> allUsers(){
		return repository.findAll();
	}
	
	@GetMapping("/clients/usernames")
	public List<String> allUsernames(){
		List<Client> clientes = this.clientService.findAllClients();
		List<String> usernames = new ArrayList<>();
		for(Client c: clientes) {
			usernames.add(c.getUser().getUsername());
		}
		return usernames;
	}
	
	@PostMapping(value="/clients/save")
	public Client saveClient(@RequestBody Client client) {
		User u = this.userService.save(client.getUser());
		this.authoritiesService.saveAuthorities(u.getUsername(), "client");
		client.setUser(u);
		return this.clientService.save(client);
	}
	
	@GetMapping("/clients/profile")
	public Client clientDetails() {
		String username = userController.getCurrentUsername();
		Optional<Client> result = this.clientService.findClientByUsername(username);
		Client client = new Client();
		if(result.isPresent()) {
			client = result.get();
		}
		return client;
	}
	
	@PutMapping("/clients/profile")
	public void updateClient(@RequestBody Client client) {
		this.clientService.save(client);
	}

}
