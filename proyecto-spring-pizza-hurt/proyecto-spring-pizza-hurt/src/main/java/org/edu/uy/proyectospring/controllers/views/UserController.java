package org.edu.uy.proyectospring.controllers.views;

import org.edu.uy.proyectospring.entities.UserEntity;
import org.edu.uy.proyectospring.models.CardDTO;
import org.edu.uy.proyectospring.models.UserDTO;
import org.edu.uy.proyectospring.models.UserRegistrationDTO;
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
import org.springframework.web.bind.support.SessionStatus;

import jakarta.validation.Valid;

@Controller
public class UserController {
	
	private final UserService userService;
	
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@GetMapping("/login")
	public String showLogin(@ModelAttribute("userDTO")UserDTO userDTO) {
		return "login";
	}
	
	@GetMapping("/profile")
    public String showProfile(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName(); // Esto te dará el nombre de usuario

        UserEntity userEntity = userService.loadUserEntityByUsername(currentUserName);

        model.addAttribute("user", userEntity);
        model.addAttribute("cards", userEntity.getCards());
        
        model.addAttribute("CardDTO", new CardDTO());

        return "profile"; //
    }
	
	@PostMapping("/signin")
	public String addUser(@ModelAttribute("userRegistrationDTO") @Valid UserRegistrationDTO userRegistrationDTO, BindingResult bindingResult, Model model, SessionStatus sessionStatus) {
		if(bindingResult.hasErrors()) {
			//IMPORTANTE. SI REDIRECCIONAMOS PERDEMOS LOS ERRORES (a menos q se lo pasemos)
			return "adduser";
		}
		else {
			try {
				userService.createUser(userRegistrationDTO);
			}catch(Exception ex) {
				bindingResult.reject("errorCode", "Hubo una inconsistencia en algunos de los datos ingresados..., se sugiere refrescar y volver a intentar");
				return "adduser";
			}
			sessionStatus.setComplete();
			return "redirect:/carrito/pizza";
			//return "adduser";
		}
	}
	
	@GetMapping("/signin")
	public String showAddUser(@ModelAttribute("userRegistrationDTO")UserRegistrationDTO userRegistrationDTO) {
		return "adduser";
	}
}
