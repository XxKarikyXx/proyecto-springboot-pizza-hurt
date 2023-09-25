package org.edu.uy.proyectospring.services;

import java.util.Date;
import java.util.List;

import org.edu.uy.proyectospring.converters.OrderConverter;
import org.edu.uy.proyectospring.converters.OrderDTOConverter;
import org.edu.uy.proyectospring.entities.OrderEntity;
import org.edu.uy.proyectospring.entities.UserEntity;
import org.edu.uy.proyectospring.models.OrderDTO;
import org.edu.uy.proyectospring.repositories.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
	
	private UserService userService;
	
	private OrderRepository orderRepository;
	
	private OrderConverter orderConverter;
	
	private OrderDTOConverter orderDTOConverter;
	
	private PizzaComponentService pizzaComponentService;

	public OrderService(OrderRepository orderRepository, UserService userService, OrderConverter orderConverter, OrderDTOConverter orderDTOConverter,
			PizzaComponentService pizzaComponentService) {
		super();
		this.userService = userService;
		this.orderRepository = orderRepository;
		this.orderConverter = orderConverter;
		this.orderDTOConverter = orderDTOConverter;
		this.pizzaComponentService = pizzaComponentService;
	}
	
	//Hay que analizar esto.
	public OrderDTO saveOrderWithUserId(OrderDTO orderPizza, Long userId) {
		//Hay que acordarse de calcular el total aqui que seria la suma de cada componente.
		UserEntity user = userService.getUserById(userId);
		OrderEntity orderToSave = orderConverter.convert(orderPizza);
		orderToSave.setOrderDateTime(new Date());
		orderToSave.setUser(user);
		return orderDTOConverter.convert(orderRepository.save(orderToSave));
	}

	public List<OrderEntity> getOrdersByUserId(Long userId) {
		return orderRepository.findByUserId(userId);
	}

}
