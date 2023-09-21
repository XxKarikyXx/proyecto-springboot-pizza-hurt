package org.edu.uy.proyectospring.controllers.views;


import org.edu.uy.proyectospring.models.UserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
public class HomeController {
	
	
	@GetMapping("/ejemplo")
	public String getIndex(@RequestParam(name="nombre") String saludo, Model model) {
		model.addAttribute("saludo", saludo);
		return "index";
	}
	
	@GetMapping("/")
	public String getIndex1() {
		return "index";
	}
}
