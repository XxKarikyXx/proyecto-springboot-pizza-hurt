package org.edu.uy.proyectospring.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import org.edu.uy.proyectospring.converters.CardConverter;
import org.edu.uy.proyectospring.converters.CardDTOConverter;
import org.edu.uy.proyectospring.converters.UserConverter;
import org.edu.uy.proyectospring.converters.UserRegistrationConverter;
import org.edu.uy.proyectospring.entities.Card;
import org.edu.uy.proyectospring.entities.OrderEntity;
import org.edu.uy.proyectospring.entities.UserEntity;
import org.edu.uy.proyectospring.exceptions.EntityNotFoundException;
import org.edu.uy.proyectospring.models.CardDTO;
import org.edu.uy.proyectospring.models.UserDTO;
import org.edu.uy.proyectospring.models.UserRegistrationDTO;
import org.edu.uy.proyectospring.repositories.CardRepository;
import org.edu.uy.proyectospring.repositories.PaymentRepository;
import org.edu.uy.proyectospring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.validation.Valid;

@Service
public class UserService implements UserDetailsService {
	

	//@Autowired
	private final UserRepository userRepository;
	
	//@Autowired
	private final CardRepository cardRepository;
	
	private UserConverter userConverter;
	
	private UserRegistrationConverter userRegistrationConverter;
	
	private CardDTOConverter cardDTOConverter;
	
	private AuthenticationContext autentication;

	public UserDetails loadByUsername(String username) throws UsernameNotFoundException {
		
		//UserEntity
		
		//return Optional.ofNullable(userRepository)
		return null;
	}

	public UserService(UserRepository userRepository, CardRepository cardRepository, UserConverter userConverter,
			UserRegistrationConverter userRegistrationConverter, CardDTOConverter cardDTOConverter, AuthenticationContext autentication) {
		super();
		this.userRepository = userRepository;
		this.cardRepository = cardRepository;
		this.userConverter = userConverter;
		this.userRegistrationConverter = userRegistrationConverter;
		this.cardDTOConverter = cardDTOConverter;
		this.autentication = autentication;
	}

	public List<UserEntity> getUsers() {
		return userRepository.findAll();
	}

	public UserEntity createUser(@Valid UserRegistrationDTO userRegistrationDTO) {		
		UserEntity user = this.userRegistrationConverter.convert(userRegistrationDTO);
		user.setActive(true);
		user.setCards(new ArrayList<Card>());
		user.setOrders(new ArrayList<OrderEntity>());
		return userRepository.save(user);
	}

	public UserEntity getUserById(Long id) {
		return userRepository.findById(id)
				.orElseThrow(()-> new EntityNotFoundException(id));
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return Optional.ofNullable(userRepository.findByEmail(username))
				       .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
	}
	
	public UserEntity loadUserEntityByUsername(String username) throws UsernameNotFoundException {
		return Optional.ofNullable(userRepository.findByEmail(username))
				       .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
	}
	
	@Transactional
	public UserEntity agregarUsuario(UserRegistrationDTO usuarioDTO) throws Exception{
		
		UserEntity userValidation = userRepository.findByEmail(usuarioDTO.getEmail());
		
		if (userValidation != null) {
			
			UserRegistrationConverter converter = new UserRegistrationConverter();
			
			return userRepository.save(converter.convert(usuarioDTO));
		}
		else
		{
			throw new Exception("El usuario ya existe");
		}
	}

	public List<CardDTO> getUserCardsByUserId(long userId) {
		return getUserById(userId)
				.getCards()
				.stream()
				.map(c->cardDTOConverter.convert(c))
				.collect(Collectors.toList());
	}
	
	public UserEntity getUserLogged() {
		UserEntity user = (UserEntity) autentication.getAuthentication().getPrincipal();
		if (user == null || user.getId() == 0) {
			throw new RuntimeException("No se pudo obtener la información del usuario autenticado");
		}
		return user;
	}
	
	@Transactional
	public void addCardToUser(String username, CardDTO card) {
	    UserEntity user = this.loadUserEntityByUsername(username);
	    CardConverter cardConverter = new CardConverter();
	    
	    Card cardEntity = cardConverter.convert(card);
	    
	    user.getCards().add(cardEntity);
	    
	    cardRepository.save(cardEntity);
	    userRepository.save(user);
	}
}
