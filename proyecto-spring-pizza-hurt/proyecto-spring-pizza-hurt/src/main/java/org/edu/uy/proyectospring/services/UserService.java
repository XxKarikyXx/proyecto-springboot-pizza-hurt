package org.edu.uy.proyectospring.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.edu.uy.proyectospring.converters.UserConverter;
import org.edu.uy.proyectospring.converters.UserRegistrationConverter;
import org.edu.uy.proyectospring.entities.Card;
import org.edu.uy.proyectospring.entities.OrderEntity;
import org.edu.uy.proyectospring.entities.UserEntity;
import org.edu.uy.proyectospring.exceptions.EntityNotFoundException;
import org.edu.uy.proyectospring.models.UserDTO;
import org.edu.uy.proyectospring.models.UserRegistrationDTO;
import org.edu.uy.proyectospring.repositories.PaymentRepository;
import org.edu.uy.proyectospring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.validation.Valid;

@Service
public class UserService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	private UserConverter userConverter;
	
	private PaymentRepository paymentRepository;
	
	public UserDetails loadByUsername(String username) throws UsernameNotFoundException {
		
		//UserEntity
		
		//return Optional.ofNullable(userRepository)
		return null;
	}

	public UserService(UserRepository userRepository, UserConverter userConverter) {
		super();
		this.userRepository = userRepository;
		this.userConverter = userConverter;
	}

	public List<UserEntity> getUsers() {
		return userRepository.findAll();
	}

	public UserEntity createUser(@Valid UserDTO userEntity) {		
		UserEntity user = this.userConverter.convert(userEntity);
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
		return Optional.ofNullable(userRepository.findByUsername(username))
				       .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
	}
	
	@Transactional
	public UserEntity agregarUsuario(UserRegistrationDTO usuarioDTO) throws Exception{
		
		UserEntity userValidation = userRepository.findByUsername(usuarioDTO.getUsername());
		
		if (userValidation != null) {
			
			UserRegistrationConverter converter = new UserRegistrationConverter();
			
			return userRepository.save(converter.convert(usuarioDTO));
		}
		else
		{
			throw new Exception("El usuario ya existe");
		}
	}

}
