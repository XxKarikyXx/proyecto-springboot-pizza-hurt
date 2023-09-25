package org.edu.uy.proyectospring.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.edu.uy.proyectospring.converters.UserConverter;
import org.edu.uy.proyectospring.entities.Card;
import org.edu.uy.proyectospring.entities.OrderEntity;
import org.edu.uy.proyectospring.entities.UserEntity;
import org.edu.uy.proyectospring.exceptions.EntityNotFoundException;
import org.edu.uy.proyectospring.models.UserDTO;
import org.edu.uy.proyectospring.repositories.PaymentRepository;
import org.edu.uy.proyectospring.repositories.UserRepository;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;

@Service
public class UserService {
	
	private UserRepository userRepository;
	
	private UserConverter userConverter;
	

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

}
