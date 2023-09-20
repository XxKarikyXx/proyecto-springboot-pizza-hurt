package org.edu.uy.proyectospring.controllers.rest;

import org.edu.uy.proyectospring.models.LogInDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LogInOutControllerRest {
	
	
	@GetMapping("/login")
	public boolean logIn(LogInDTO login){
		return true;
	}
	
	@PostMapping("/logout")
	public boolean logOut() {
		return true;
	}
}
