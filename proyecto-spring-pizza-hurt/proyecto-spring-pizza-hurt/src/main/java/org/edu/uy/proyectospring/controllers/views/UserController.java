package org.edu.uy.proyectospring.controllers.views;

import org.edu.uy.proyectospring.entities.UserEntity;
import org.edu.uy.proyectospring.models.UserDTO;
import org.edu.uy.proyectospring.services.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
public class UserController {
	
	UserService userService;
	
	//private final AuthenticationManager authenticationManager;
	
	//public UserController(UserService userService, AuthenticationManager authenticationManager) {
	public UserController(UserService userService) {
		super();
		this.userService = userService;
		//this.authenticationManager = authenticationManager;
	}
	
	
	@GetMapping("/signin")
	public String showLogin(@ModelAttribute("userDTO")UserDTO userDTO) {
		return "login";
	}
	
    @PostMapping("/signin")
    public String login(@RequestParam String email, @RequestParam String password, Model model) {
    	System.out.println("login");
        try {
			 UserDetails user = userService.loadByUsername(email);
			 System.out.print(user.toString());
			 if (user == null) {
				 return "login";
			 }
            //Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
            //SecurityContextHolder.getContext().setAuthentication(authentication);
            return "redirect:/dashboard";  // o redirige a donde desees después del login
        } catch (Exception e) {
            model.addAttribute("error", "Email o contraseña incorrectos");
            return "login";
        }
    }
    
}
