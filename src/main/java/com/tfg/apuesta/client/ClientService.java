package com.tfg.apuesta.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;


@Service
public class ClientService {
	
	private ClientRepository clientRepository;
	
	@Autowired
	public ClientService(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}
	
	public Client findClientById(Integer id) throws DataAccessException {
		return clientRepository.findClientById(id);
	}
	
	public List<Client> findAllClients() throws DataAccessException {
		return clientRepository.findAll();
	}

}
