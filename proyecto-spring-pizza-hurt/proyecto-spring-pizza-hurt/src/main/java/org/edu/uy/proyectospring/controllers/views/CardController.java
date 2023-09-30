package org.edu.uy.proyectospring.controllers.views;

import org.edu.uy.proyectospring.entities.UserEntity;
import org.edu.uy.proyectospring.repositories.CardRepository;
import org.edu.uy.proyectospring.repositories.UserRepository;
import org.edu.uy.proyectospring.entities.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("loggedInUser")
public class CardController {

    @Autowired
    private UserRepository userRepository;  // Asumiendo que tienes un repositorio para UserEntity
    
    @Autowired
    private CardRepository cardRepository;  // Asumiendo que tienes un repositorio para Card

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
}

