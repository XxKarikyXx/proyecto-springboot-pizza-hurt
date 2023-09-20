package org.edu.uy.proyectospring.services;

import org.edu.uy.proyectospring.repositories.PaymentRepository;
import org.edu.uy.proyectospring.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
	
	private PaymentRepository paymentService;
	
	private UserRepository userRepository;
	
	private PizzaComponentService pizzaComponentService;

	public OrderService(PaymentRepository paymentService, UserRepository userRepository,
			PizzaComponentService pizzaComponentService) {
		super();
		this.paymentService = paymentService;
		this.userRepository = userRepository;
		this.pizzaComponentService = pizzaComponentService;
	}

}
