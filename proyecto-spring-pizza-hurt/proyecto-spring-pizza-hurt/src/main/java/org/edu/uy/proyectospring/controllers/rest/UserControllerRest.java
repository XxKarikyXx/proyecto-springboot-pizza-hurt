package org.edu.uy.proyectospring.controllers.rest;

import java.util.List;

import org.edu.uy.proyectospring.entities.UserEntity;
import org.edu.uy.proyectospring.exceptions.EntityFoundException;
import org.edu.uy.proyectospring.exceptions.EntityNotFoundException;
import org.edu.uy.proyectospring.models.CardDTO;
import org.edu.uy.proyectospring.models.UserDTO;
import org.edu.uy.proyectospring.models.UserRegistrationDTO;
import org.edu.uy.proyectospring.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class UserControllerRest {
	
	private final UserService userService;
	
	private final BCryptPasswordEncoder passwordEncoder;
	
	public UserControllerRest(UserService userService, BCryptPasswordEncoder passwordEncoder) {
		super();
		this.userService = userService;
		this.passwordEncoder = passwordEncoder;
	}
		
	@GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }
	
	@PostMapping("/users")
    public ResponseEntity<Object> addUser(@RequestBody @Valid UserRegistrationDTO userRegistrationDTO) {
        try {
        	UserDTO createdUser = userService.createUser(userRegistrationDTO, new BCryptPasswordEncoder());
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        } catch (EntityFoundException e) {
        	 return new ResponseEntity<>("Ya existe un usuario con el mismo email", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	 
    @GetMapping("users/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(userService.getUserById(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody @Valid UserDTO userDTO) {
        try {
            UserEntity userEntity = (UserEntity) userService.loadUserByUsername(userDTO.email());

            boolean isPasswordCorrect = passwordEncoder.matches(userDTO.password(), userEntity.getPassword());
            
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

    @GetMapping("users/{userId}/cards")
    public ResponseEntity<List<CardDTO>> getUserCards(@PathVariable long userId) {
        List<CardDTO> cards = userService.getUserCardsByUserId(userId);
        return ResponseEntity.ok(cards);
    }

    @PostMapping("users/{userId}/cards")
    public ResponseEntity<Object> addCard(@PathVariable Long userId, @RequestBody @Valid CardDTO cardDTO) {
        try {
        	CardDTO card = userService.addCardToUserById(userId, cardDTO);
        	return new ResponseEntity<>(card, HttpStatus.CREATED);
        } catch(EntityNotFoundException e){
        	return new ResponseEntity<>("El usuario no existe",HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
        	return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
}
