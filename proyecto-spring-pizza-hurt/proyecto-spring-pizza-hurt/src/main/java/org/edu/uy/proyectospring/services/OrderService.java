package org.edu.uy.proyectospring.services;

import java.util.Date;
import java.util.List;

import org.edu.uy.proyectospring.converters.OrderConverter;
import org.edu.uy.proyectospring.converters.OrderDTOConverter;
import org.edu.uy.proyectospring.converters.OrderWithIdDTOConverter;
import org.edu.uy.proyectospring.entities.OrderEntity;
import org.edu.uy.proyectospring.entities.UserEntity;
import org.edu.uy.proyectospring.exceptions.EntityNotFoundException;
import org.edu.uy.proyectospring.models.OrderDTO;
import org.edu.uy.proyectospring.models.OrderWithIdDTO;
import org.edu.uy.proyectospring.repositories.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
	
	private UserService userService;
	
	private OrderRepository orderRepository;
	
	private OrderConverter orderConverter;
	
	private OrderDTOConverter orderDTOConverter;
	
	private OrderWithIdDTOConverter orderWithIdDTOConverter;
	
	private PizzaComponentService pizzaComponentService;

	public OrderService(OrderRepository orderRepository, UserService userService, OrderConverter orderConverter, OrderDTOConverter orderDTOConverter,
			PizzaComponentService pizzaComponentService, OrderWithIdDTOConverter orderWithIdDTOConverter) {
		super();
		this.userService = userService;
		this.orderRepository = orderRepository;
		this.orderConverter = orderConverter;
		this.orderDTOConverter = orderDTOConverter;
		this.pizzaComponentService = pizzaComponentService;
		this.orderWithIdDTOConverter = orderWithIdDTOConverter;
	}
	
	//Hay que revisar esto.
	public OrderDTO saveOrderWithUserId(OrderDTO orderPizza, Long userId) {
		UserEntity user = userService.getUserById(userId);
		OrderEntity orderToSave = orderConverter.convert(orderPizza);
		orderToSave.getPizzas().forEach(p->
			orderToSave.setTotalPrice(
					orderToSave.getTotalPrice() +
					pizzaComponentService.getTotalOfPizza(p)
					)
		);
		orderToSave.setOrderDateTime(new Date());
		orderToSave.setUser(user);
		return orderDTOConverter.convert(orderRepository.save(orderToSave));
	}

	public List<OrderEntity> getOrdersByUserId(Long userId) {
		return orderRepository.findByUserId(userId);
	}

	public OrderWithIdDTO getOrderByIdAndUserId(Long orderId, Long userId) {
		OrderEntity order = orderRepository.findById(orderId)
				.orElseThrow(()->new EntityNotFoundException(orderId));
		
		if (order.getUser().getId() != userId) {
			throw new EntityNotFoundException(orderId);
		}
		
		return orderWithIdDTOConverter
				.convert(order);
	}

}
