package com.tfg.apuesta.user;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

	private UserRepository userRepository;
	

	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public User save(User user) throws DataAccessException {
		user.setEnabled(true);
		User u = this.userRepository.save(user);
		return u;
	}
	
	public Optional<User> findUser(String username) {
		return userRepository.findById(username);
	}
}
