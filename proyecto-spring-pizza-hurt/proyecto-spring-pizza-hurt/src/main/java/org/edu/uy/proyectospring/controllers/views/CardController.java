package org.edu.uy.proyectospring.controllers.views;

import org.edu.uy.proyectospring.entities.UserEntity;
import org.edu.uy.proyectospring.models.CardDTO;
import org.edu.uy.proyectospring.repositories.CardRepository;
import org.edu.uy.proyectospring.repositories.UserRepository;
import org.edu.uy.proyectospring.services.UserService;
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
@SessionAttributes("loggedInUser")
public class CardController {
	
	private final  UserService userService;
	
	public CardController(UserService userService)
	{
		super();
		this.userService = userService;
	}

    @PostMapping("/profile/addCard")
    public String addCard(@Valid @ModelAttribute("CardDTO") CardDTO cardDTO, BindingResult bindingResult, Model model, SessionStatus sessionStatus) {
    	
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName(); // Esto te dará el nombre de usuario

        UserEntity userEntity = userService.loadUserEntityByUsername(currentUserName);

        model.addAttribute("user", userEntity);
        model.addAttribute("cards", userEntity.getCards());
    	
        if (bindingResult.hasErrors()) {
            return "profile";
        }
        else {
			try {
				userService.addCardToUser(currentUserName, cardDTO);
			} catch(Exception ex) {
				bindingResult.reject("errorCode", "Hubo una inconsistencia en algunos de los datos ingresados..., se sugiere refrescar y volver a intentar");
				ex.printStackTrace();
				return "profile";
			}
			sessionStatus.setComplete();
			
			model.addAttribute("CardDTO", new CardDTO());
			return "profile";
        }
    }
	
    /*
    @GetMapping("/addCard")
    public String showAddCard(Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        UserEntity user = userRepository.findByEmail(email);

        model.addAttribute("loggedInUser", user);

        return "addCard";
    }

    @PostMapping("/addCard")
    public String addCardSubmit(Card card, Model model) {
        UserEntity loggedInUser = (UserEntity) model.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            // No hay usuario conectado. Redireccionar al login? Se hace acá o por configuración
            return "redirect:/login";
        }
        loggedInUser.getCards().add(card);
        userRepository.save(loggedInUser);
        return "redirect:/userProfile";  // Redirige al perfil del usuario
    }
    */
        
}

