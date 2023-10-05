package org.edu.uy.proyectospring.controllers.rest;

import java.util.List;

import org.edu.uy.proyectospring.entities.UserEntity;
import org.edu.uy.proyectospring.exceptions.EntityNotFoundException;
import org.edu.uy.proyectospring.models.UserDTO;
import org.edu.uy.proyectospring.models.UserRegistrationDTO;
import org.edu.uy.proyectospring.services.UserService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;


@RestController
public class UserControllerRest {
	
	private final UserService userService;
	
	public UserControllerRest(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@GetMapping("/usuarios")
	public List<UserEntity> getUsers(){
		return userService.getUsers();
	}
	
	@PostMapping("/usuarios")
	public UserDTO addUser(@RequestBody @Valid UserRegistrationDTO userRegistrationDTO) {
		return userService.createUser(userRegistrationDTO);
	}
	 
	@GetMapping("/usuarios/{id}")
	public UserDTO getUserById(@PathVariable Long id) {
		return userService.getUserById(id);
	}

}
