package org.edu.uy.proyectospring.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import org.edu.uy.proyectospring.converters.CardConverter;
import org.edu.uy.proyectospring.converters.CardDTOConverter;
import org.edu.uy.proyectospring.converters.UserDTOConverter;
import org.edu.uy.proyectospring.converters.UserRegistrationConverter;
import org.edu.uy.proyectospring.entities.Card;
import org.edu.uy.proyectospring.entities.OrderEntity;
import org.edu.uy.proyectospring.entities.UserEntity;
import org.edu.uy.proyectospring.exceptions.EntityFoundException;
import org.edu.uy.proyectospring.exceptions.EntityNotFoundException;
import org.edu.uy.proyectospring.models.CardDTO;
import org.edu.uy.proyectospring.models.UserDTO;
import org.edu.uy.proyectospring.models.UserRegistrationDTO;
import org.edu.uy.proyectospring.repositories.CardRepository;
import org.edu.uy.proyectospring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.servlet.http.HttpSession;

@Service
public class UserService implements UserDetailsService {
	
	private final UserRepository userRepository;

	private final CardRepository cardRepository;
	
	private final UserDTOConverter userDTOConverter;
	
	private final UserRegistrationConverter userRegistrationConverter;
	
	private final CardDTOConverter cardDTOConverter;
	

	public List<UserDTO> getUsers() {
		return userRepository.findAll().stream()
				.map(u->userDTOConverter.convert(u))
				.collect(Collectors.toList());
	}

	public UserService(UserRepository userRepository, CardRepository cardRepository, UserDTOConverter userDTOConverter,
			UserRegistrationConverter userRegistrationConverter, CardDTOConverter cardDTOConverter) {
		super();
		this.userRepository = userRepository;
		this.cardRepository = cardRepository;
		this.userDTOConverter = userDTOConverter;
		this.userRegistrationConverter = userRegistrationConverter;
		this.cardDTOConverter = cardDTOConverter;
	}

	public UserDTO getUserById(Long id) {
		return userDTOConverter.convert(getUserEntityById(id));
	}
	
	public UserEntity getUserEntityById(Long id) {
		return userRepository.findById(id)
				.orElseThrow(()-> new EntityNotFoundException(id));
	}

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return Optional.ofNullable(userRepository.findByEmail(username))
				       .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
	}	
	
	@Transactional
	public UserDTO createUser(UserRegistrationDTO usuarioDTO, BCryptPasswordEncoder passwordEncoder){
		try {
			loadUserByUsername(usuarioDTO.getEmail());	
			throw new EntityFoundException();
		}catch(UsernameNotFoundException ex) {
			UserEntity user = userRegistrationConverter.convert(usuarioDTO);
			user.setActive(true);
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			user.setCards(new ArrayList<Card>());
			user.setOrders(new ArrayList<OrderEntity>());
			return userDTOConverter.convert(userRepository.save(user));
		}
	}

	public List<CardDTO> getUserCardsByUserId(long userId) {
		return getUserEntityById(userId)
				.getCards()
				.stream()
				.map(c->cardDTOConverter.convert(c))
				.collect(Collectors.toList());
	}

	
	@Transactional
	public CardDTO addCardToUser(String username, CardDTO card) {
	    UserEntity user = (UserEntity) this.loadUserByUsername(username);
	    return cardDTOConverter.convert(addCardToUserEntity(user, card));
	}

	@Transactional
	public CardDTO addCardToUserById(Long userId, CardDTO cardDTO) {
	    UserEntity user = getUserEntityById(userId);
	    return cardDTOConverter.convert(addCardToUserEntity(user, cardDTO));
	}

	private Card addCardToUserEntity(UserEntity user, CardDTO cardDTO) {
	    CardConverter cardConverter = new CardConverter();
	    Card cardEntity = cardConverter.convert(cardDTO);
	    
	    user.getCards().add(cardEntity);
	    
	    Card card = cardRepository.save(cardEntity);
	    userRepository.save(user);
	    return card;
	}
}
