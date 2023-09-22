package org.edu.uy.proyectospring.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.edu.uy.proyectospring.converters.UserConverter;
import org.edu.uy.proyectospring.entities.Card;
import org.edu.uy.proyectospring.entities.OrderEntity;
import org.edu.uy.proyectospring.entities.UserEntity;
import org.edu.uy.proyectospring.models.UserDTO;
import org.edu.uy.proyectospring.repositories.PaymentRepository;
import org.edu.uy.proyectospring.repositories.UserRepository;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;

@Service
public class UserService {
	
	private UserRepository userRepository;
	
	private UserConverter userConverter;
	
	private PaymentRepository paymentRepository;
	
	

	public UserService(UserRepository userRepository, PaymentRepository paymentRepository) {
		super();
		this.userRepository = userRepository;
		this.paymentRepository = paymentRepository;
		this.userConverter = new UserConverter();
	}

	public List<UserEntity> getUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	public UserEntity createUser(@Valid UserDTO userEntity) {		
		UserEntity user = this.userConverter.convert(userEntity);
		user.setActive(true);
		user.setCards(new ArrayList<Card>());
		user.setOrders(new ArrayList<OrderEntity>());
		return userRepository.save(user);
	}

	public Optional<UserEntity> getUserById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
