package com.tfg.apuesta.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin("http://localhost:8081/")
public class UserController {

	private final UserService userService;
	
	@Autowired
	private UserRepository repository;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@GetMapping("/users")
	public List<User> allUsers(){
		return repository.findAll();
	}
	
	@PostMapping(value="/users/save")
	public User saveUser(@RequestBody User user) {
		return this.userService.save(user);
	}

}
