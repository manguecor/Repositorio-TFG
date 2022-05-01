package com.tfg.apuesta.client;

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

@RestController
@CrossOrigin("http://localhost:8081/")
public class ClientController {
	
	private final ClientService clientService;
	
	@Autowired
	private ClientRepository repository;
	
	@Autowired
	public ClientController(ClientService clientService) {
		this.clientService = clientService;
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
	
	@PostMapping(value="/clients/save")
	public Client saveClient(@RequestBody Client client) {
		return this.clientService.save(client);
	}

}
