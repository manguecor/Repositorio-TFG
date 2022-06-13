package com.tfg.apuesta.client;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClientRepository extends JpaRepository<Client,Integer>{
	
	@Query("SELECT DISTINCT client FROM Client client WHERE client.id=:id")
	Client findClientById(int id);
	
	@Query("SELECT client FROM Client client WHERE client.user.username=:username")
	Optional<Client> findClientByUsername(String username);
}
