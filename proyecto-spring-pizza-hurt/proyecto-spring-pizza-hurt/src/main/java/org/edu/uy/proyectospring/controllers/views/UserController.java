package org.edu.uy.proyectospring.controllers.views;

import org.edu.uy.proyectospring.entities.UserEntity;
import org.edu.uy.proyectospring.models.UserDTO;
import org.edu.uy.proyectospring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
	
	@PostMapping("/signin")
	public String addUser(@ModelAttribute("userDTO") @Valid UserDTO userDTO, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			//IMPORTANTE. SI REDIRECCIONAMOS PERDEMOS LOS ERRORES (a menos q se lo pasemos)
			return "adduser";
		}else {
			try {
				userService.createUser(userDTO);
			}catch(Exception ex) {
				return "adduser";
				//Otros errores. Hay que ver de pasarlo al modelo...
			}
			return "redirect:/";		
		}
	}
	
	@GetMapping("/signin")
	public String showAddUser(@ModelAttribute("userDTO")UserDTO userDTO) {
		return "adduser";
	}
	
	@GetMapping("/profile")
    public String showProfile(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName(); // Esto te dará el nombre de usuario

        UserEntity userEntity = userService.loadUserEntityByUsername(currentUserName);

        model.addAttribute("user", userEntity);

        return "profile"; //
    }
	
	/*
	@GetMapping("/login")
	public String showLogin(@ModelAttribute("userDTO")UserDTO userDTO) {
		return "login";
	}
	
    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, Model model) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return "redirect:/index";  // o redirige a donde desees después del login
        } catch (Exception e) {
            model.addAttribute("error", "Email o contraseña incorrectos");
            return "login";
        }
    }
    */
}
