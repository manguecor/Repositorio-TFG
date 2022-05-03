package com.tfg.apuesta.user;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class AuthoritiesService {

	private AuthoritiesRepository authoritiesRepository;
	private UserService userService;

	@Autowired
	public AuthoritiesService(AuthoritiesRepository authoritiesRepository,UserService userService) {
		this.authoritiesRepository = authoritiesRepository;
		this.userService = userService;
	}

	@Transactional
	public void saveAuthorities(Authorities authorities) throws DataAccessException {
		authoritiesRepository.save(authorities);
	}
	
	@Transactional
	public void saveAuthorities(String username, String role) throws DataAccessException {
		Authorities authority = new Authorities();
		Optional<User> user = userService.findUser(username);
		if(user.isPresent()) {
			//authority.setUser(user.get());
			authority.setAuthority(role);
			Set<Authorities> auth = new HashSet<>();
			auth.add(authority);
			user.get().setAuthorities(auth);
			authoritiesRepository.save(authority);
		}else
			throw new DataAccessException("User '"+username+"' not found!") {};
	}


}
