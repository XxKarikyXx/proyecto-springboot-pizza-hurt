package org.edu.uy.proyectospring.controllers.rest;

import java.util.List;

import org.edu.uy.proyectospring.converters.UserConverter;
import org.edu.uy.proyectospring.converters.UserDTOConverter;
import org.edu.uy.proyectospring.entities.UserEntity;
import org.edu.uy.proyectospring.exceptions.EntityNotFoundException;
import org.edu.uy.proyectospring.models.CardDTO;
import org.edu.uy.proyectospring.models.UserDTO;
import org.edu.uy.proyectospring.models.UserRegistrationDTO;
import org.edu.uy.proyectospring.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
public class UserControllerRest {
	
	private final UserService userService;
	
	private final BCryptPasswordEncoder passwordEncoder;
	
	public UserControllerRest(UserService userService, BCryptPasswordEncoder passwordEncoder) {
		super();
		this.userService = userService;
		this.passwordEncoder = passwordEncoder;
	}
		
	@PostMapping("/")
    public ResponseEntity<UserDTO> addUser(@RequestBody @Valid UserRegistrationDTO userRegistrationDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            UserEntity createdUser = userService.createUser(userRegistrationDTO);
            UserDTOConverter converter = new UserDTOConverter();
            UserDTO createdUserDTO = converter.convert(createdUser);
            return new ResponseEntity<>(createdUserDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	 
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        try {
        	UserDTOConverter converter = new UserDTOConverter();
            UserEntity userEntity = userService.getUserById(id);
            UserDTO userDTO = converter.convert(userEntity);
            return ResponseEntity.ok(userDTO);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/login")
    public ResponseEntity<String> loginUser(@RequestParam String username, @RequestParam String password) {
        try {
            UserEntity userEntity = userService.loadUserEntityByUsername(username);

            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            boolean isPasswordCorrect = passwordEncoder.matches(password, userEntity.getPassword());
            
            if (!isPasswordCorrect) {
                return new ResponseEntity<>("Contraseña incorrecta", HttpStatus.UNAUTHORIZED);
            }

            if (!userEntity.isActive()) {
                return new ResponseEntity<>("Usuario inactivo", HttpStatus.FORBIDDEN);
            }

            return new ResponseEntity<>("OK", HttpStatus.OK);
        } catch (UsernameNotFoundException e) {
            return new ResponseEntity<>("Usuario no encontrado", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
    // Obtener todas las tarjetas para un usuario dado
    @GetMapping("/{userId}/cards")
    public ResponseEntity<List<CardDTO>> getUserCards(@PathVariable long userId) {
        List<CardDTO> cards = userService.getUserCardsByUserId(userId);
        return ResponseEntity.ok(cards);
    }

    // Agregar una nueva tarjeta a un usuario
    @PostMapping("/{userId}/cards")
    public ResponseEntity<String> addCard(@PathVariable Long userId, @RequestBody CardDTO cardDTO) {
        try {
            userService.addCardToUserById(userId, cardDTO);
            return ResponseEntity.ok("Tarjeta agregada con éxito");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al agregar la tarjeta");
        }
    }
	
}
