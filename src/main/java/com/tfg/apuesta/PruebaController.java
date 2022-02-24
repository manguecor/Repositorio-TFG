package com.tfg.apuesta;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PruebaController {
	
	@GetMapping("/prueba")
	public String index() {
		return "hola";
	}
	
	@GetMapping("/users/neww")
	public String index2() {
		return "createUserForm";
	}
	
	@RequestMapping("/hola")
	 public String hola(Model modelo) {
	   modelo.addAttribute("mensaje","hola desde thymeleaf");
	   return "hola";
	 }

}
