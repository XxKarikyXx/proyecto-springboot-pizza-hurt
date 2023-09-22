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
	
	@PostMapping("/signin")
	public String addUser(@ModelAttribute("userDTO") @Valid UserDTO userDTO, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			//IMPORTANTE. SI REDIRECCIONAMOS PERDEMOS LOS ERRORES (a menos q se lo pasemos)
			return "formexample";
		}else {
			try {
				userService.createUser(userDTO);
			}catch(Exception ex) {
				return "formexample";
				//Otros errores. Hay que ver de pasarlo al modelo...
			}
			return "redirect:/";		
		}
	}
	
	
	@GetMapping("/signin")
	public String showAddUser(@ModelAttribute("userDTO")UserDTO userDTO) {
		return "formexample";
	}
}
