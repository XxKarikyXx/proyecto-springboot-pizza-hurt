package org.edu.uy.proyectospring.controllers.views;

import org.edu.uy.proyectospring.entities.UserEntity;
import org.edu.uy.proyectospring.models.CardDTO;
import org.edu.uy.proyectospring.models.UserDTO;
import org.edu.uy.proyectospring.repositories.CardRepository;
import org.edu.uy.proyectospring.repositories.UserRepository;
import org.edu.uy.proyectospring.services.AuthorizationService;
import org.edu.uy.proyectospring.services.UserService;

import java.util.List;

import org.edu.uy.proyectospring.entities.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import jakarta.validation.Valid;

@Controller
public class CardController {
	
	private final UserService userService;
	
	private final AuthorizationService authorizationService;
	
	public CardController(UserService userService, AuthorizationService authorizationService)
	{
		super();
		this.userService = userService;
		this.authorizationService = authorizationService;
	}

    @PostMapping("/profile")
    public String addCard(@Valid @ModelAttribute("CardDTO") CardDTO cardDTO, BindingResult bindingResult, Model model) {
        UserDTO userDTO = authorizationService.getUserDTOLogged();
        List<CardDTO> cards = userService.getUserCardsByUserId(userDTO.id());

        model.addAttribute("user", userDTO);
        model.addAttribute("cards", cards);
    	
        if (bindingResult.hasErrors()) {
            return "profile";
        }
        else {
			try {
				userService.addCardToUserById(userDTO.id(), cardDTO);
			} catch(Exception ex) {
				bindingResult.reject("errorCode", ex.getMessage());
				return "profile";
			}
			
			return "redirect:/profile";
        }
    }
}

