package org.edu.uy.proyectospring.controllers.views;

import java.util.List;

import org.edu.uy.proyectospring.exceptions.EntityFoundException;
import org.edu.uy.proyectospring.models.CardDTO;
import org.edu.uy.proyectospring.models.UserDTO;
import org.edu.uy.proyectospring.models.UserRegistrationDTO;
import org.edu.uy.proyectospring.services.AuthorizationService;
import org.edu.uy.proyectospring.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
public class UserController {
	
	private final UserService userService;
	
	private final AuthorizationService authorizationService;
	
	public UserController(UserService userService, AuthorizationService authorizationService) {
		super();
		this.userService = userService;
		this.authorizationService = authorizationService;
	}
	
	@GetMapping("/login")
	public String showLogin(@ModelAttribute("userDTO")UserRegistrationDTO userDTO) {
		return "login";
	}
	

	@PostMapping("/login")
	public String authLogin(HttpServletRequest request, @ModelAttribute("userDTO") @Valid UserDTO userDTO, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "login";
		}
		try {
			authorizationService.authenticateUser(userDTO, request);
		}catch(Exception ex) {
			bindingResult.reject("error","Credenciales erróneas");
			return "login";
		}
		return "redirect:/profile";
	}

	
	
	@GetMapping("/profile")
    public String showProfile(Model model) {
        
        UserDTO userDTO = authorizationService.getUserDTOLogged();
        List<CardDTO> cards = userService.getUserCardsByUserId(userDTO.id());

        model.addAttribute("user", userDTO);
        model.addAttribute("cards", cards);
        
        model.addAttribute("CardDTO", new CardDTO());
       
        return "profile";
    }
	
	@PostMapping("/signin")
	public String addUser(@ModelAttribute("userRegistrationDTO") @Valid UserRegistrationDTO userRegistrationDTO, BindingResult bindingResult, Model model, SessionStatus sessionStatus) {
		if(bindingResult.hasErrors()) {
			return "adduser";
		}
		else {
			try {
				userService.createUser(userRegistrationDTO);
			}catch(EntityFoundException ex) {
				bindingResult.reject("errorCode", "El usuario ya existe");
				return "adduser";
			}
			sessionStatus.setComplete();
			return "redirect:/carrito/pizza";
		}
	}
	
	@GetMapping("/signin")
	public String showAddUser(@ModelAttribute("userRegistrationDTO")UserRegistrationDTO userRegistrationDTO) {
		return "adduser";
	}
}
