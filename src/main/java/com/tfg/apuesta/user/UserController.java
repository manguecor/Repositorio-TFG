package com.tfg.apuesta.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
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
	
	private AuthoritiesService authoritiesService;
	
	@Autowired
	private UserRepository repository;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private MyUserDetailsService userDetailsService;
    
    @Autowired
    private JwtUtil jwtUtil;

	@Autowired
	public UserController(UserService userService, AuthoritiesService authoritiesService) {
		this.userService = userService;
		this.authoritiesService = authoritiesService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@GetMapping("/users")
	public List<User> allUsers(){
		return repository.findAll();
	}
	
	/*@PostMapping(value="/users/save")
	public User saveUser(@RequestBody User user) {
		User u = this.userService.save(user);
		this.authoritiesService.saveAuthorities(user.getUsername(), "client");
		return u;
	}*/
	
	@PostMapping("/login")
    public ResponseEntity<?> signIn(@RequestBody AuthenticationRequest authRequest) throws Exception{

        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
        }catch (BadCredentialsException ex){throw new Exception("Error: username or password incorrect");}

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
        final  String token = jwtUtil.createToken(userDetails);
        User user = userService.findUserByUserName(authRequest.getUsername());


        return ResponseEntity.ok(new AuthenticationResponse(token, user));
    }

}
