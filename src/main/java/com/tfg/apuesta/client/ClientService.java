package com.tfg.apuesta.client;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.tfg.apuesta.league.League;
import com.tfg.apuesta.player.Player;
import com.tfg.apuesta.user.User;


@Service
public class ClientService {
	
	private ClientRepository clientRepository;
	
	@Autowired
	public ClientService(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}
	
	public Client save(Client client) throws DataAccessException {
		Client c = this.clientRepository.save(client);
		return c;
	}
	
	public Client findClientById(Integer id) throws DataAccessException {
		return clientRepository.findClientById(id);
	}
	
	public List<Client> findAllClients() throws DataAccessException {
		return clientRepository.findAll();
	}
	
	public Optional<Client> findClientByUsername(String username) throws DataAccessException {
		return clientRepository.findClientByUsername(username);
	}
}
