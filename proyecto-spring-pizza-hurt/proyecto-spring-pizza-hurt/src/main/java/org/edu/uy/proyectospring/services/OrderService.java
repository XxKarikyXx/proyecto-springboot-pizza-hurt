package org.edu.uy.proyectospring.services;

import org.edu.uy.proyectospring.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
	
	private UserRepository userRepository;
	
	private PizzaComponentService pizzaComponentService;

	public OrderService(UserRepository userRepository,
			PizzaComponentService pizzaComponentService) {
		super();
		this.userRepository = userRepository;
		this.pizzaComponentService = pizzaComponentService;
	}

}
