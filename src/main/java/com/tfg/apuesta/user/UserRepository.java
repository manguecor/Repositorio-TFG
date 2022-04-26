package com.tfg.apuesta.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends  JpaRepository<User, Long>{
	
	@Query("SELECT u FROM User u WHERE u.username=:username")
	Optional<User> findById(String username);
	
	@Query("SELECT u FROM User u WHERE u.username = ?1")
	Optional<User> findUserByUserName(String username);

	@Query("SELECT u FROM User u WHERE u.username = ?1")
	boolean existsUserByUserName(String username);
	
}