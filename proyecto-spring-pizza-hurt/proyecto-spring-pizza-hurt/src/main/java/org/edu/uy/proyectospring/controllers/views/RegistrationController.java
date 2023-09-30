package org.edu.uy.proyectospring.controllers.views;

import org.edu.uy.proyectospring.models.UserRegistrationDTO;
import org.edu.uy.proyectospring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;

@Controller
public class RegistrationController {

	@Autowired
	private UserService userService;

	@GetMapping("/registrarse")
	public String getView(Model model) {
		model.addAttribute("formDTO", new UserRegistrationDTO());
		return "registration";
	}
	
	@PostMapping("/registrarse")
	public String procesarForm(@ModelAttribute @Valid UserRegistrationDTO userRegistrationDTO, BindingResult errores) {
		if (errores.hasErrors()) {
			System.out.println("Errores en la validacion:" + errores);
			return "/registrarse";
		}
		else {
			try {
				userService.agregarUsuario(userRegistrationDTO);
				return "redirect:/";
			}
			catch (Exception e) {
				errores.reject("usuarioRepetido", e.getMessage());
				return "/registrarse";
			}
		}
			
	}
	
}
