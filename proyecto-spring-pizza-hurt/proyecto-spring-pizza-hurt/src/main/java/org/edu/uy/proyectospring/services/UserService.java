package org.edu.uy.proyectospring.services;

import java.util.List;
import java.util.Optional;

import org.edu.uy.proyectospring.entities.UserEntity;
import org.edu.uy.proyectospring.models.UserDTO;
import org.edu.uy.proyectospring.repositories.PaymentRepository;
import org.edu.uy.proyectospring.repositories.UserRepository;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;

@Service
public class UserService {
	
	private UserRepository userRepository;
	
	private PaymentRepository paymentRepository;
	
	

	public UserService(UserRepository userRepository, PaymentRepository paymentRepository) {
		super();
		this.userRepository = userRepository;
		this.paymentRepository = paymentRepository;
	}

	public List<UserEntity> getUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	public UserEntity createUser(@Valid UserDTO userEntity) {
		
		UserEntity user = new UserEntity();
		user.setEmail(userEntity.email());
		return userRepository.save(user);
	}

	public Optional<UserEntity> getUserById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
