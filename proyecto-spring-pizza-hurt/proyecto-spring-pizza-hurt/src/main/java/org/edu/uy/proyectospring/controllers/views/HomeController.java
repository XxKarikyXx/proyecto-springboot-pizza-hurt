package org.edu.uy.proyectospring.controllers.views;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	//No funciona con los errores porque dice que le falta recibir algo...
	@GetMapping("/form")
	public String getForm() {
		return "formexample";
	}
}
