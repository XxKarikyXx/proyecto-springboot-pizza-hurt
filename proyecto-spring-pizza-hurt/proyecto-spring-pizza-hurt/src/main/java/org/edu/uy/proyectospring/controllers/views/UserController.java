package org.edu.uy.proyectospring.controllers.views;

import org.edu.uy.proyectospring.models.UserDTO;
import org.edu.uy.proyectospring.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;

@Controller
public class UserController {
	
	UserService userService;
	
	
	
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@PostMapping("/addUser")
	public String addUser(@ModelAttribute("userDTO") @Valid UserDTO userDTO, Errors errors, Model model) {
		if(errors.hasErrors()) {
			//IMPORTANTE. SI REDIRECCIONAMOS PERDEMOS LOS ERRORES
			return "formexample";
		}else {
			try {
				userService.createUser(userDTO);
			}catch(Exception ex) {
				//Da errores por las validaciones de UserEntity. Habría que pasarlas al DTO
			}
			return "redirect:/form";		
		}
	}
	
	
	@GetMapping("/form")
	public String showAddUser(@ModelAttribute("userDTO")UserDTO userDTO) {
		return "formexample";
	}
}
