package com.tfg.apuesta.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ClientController {
	
	private final ClientService clientService;
	
	@Autowired
	public ClientController(ClientService clientService) {
		this.clientService = clientService;
	}
	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@GetMapping("/client/{clientId}")
	public ModelAndView showClient(@PathVariable("clientId") int clientId) {
		ModelAndView mav = new ModelAndView("/welcome");
		mav.addObject(this.clientService.findClientById(clientId));
		return mav;
	}
	
	@GetMapping("/clients")
	public ModelAndView showAllClients() {
		ModelAndView mav = new ModelAndView("/welcome");
		mav.addObject(this.clientService.findAllClients());
		return mav;
	} 

}
