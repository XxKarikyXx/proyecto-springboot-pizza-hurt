package org.edu.uy.proyectospring.services;

import java.util.Date;
import java.util.List;

import org.edu.uy.proyectospring.converters.DeliveryConverter;
import org.edu.uy.proyectospring.converters.OrderConverter;
import org.edu.uy.proyectospring.converters.OrderDTOConverter;
import org.edu.uy.proyectospring.converters.PaymentConverter;
import org.edu.uy.proyectospring.entities.OrderEntity;
import org.edu.uy.proyectospring.entities.UserEntity;
import org.edu.uy.proyectospring.exceptions.EntityNotFoundException;
import org.edu.uy.proyectospring.models.OrderDTO;
import org.edu.uy.proyectospring.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class OrderService {
	
	private UserService userService;
	
	private OrderRepository orderRepository;
	
	private OrderConverter orderConverter;
	
	private OrderDTOConverter orderDTOConverter;
	
	private DeliveryConverter deliveryConverter;
	
	private PaymentConverter paymentConverter;
	
	private PizzaComponentService pizzaComponentService;

	
	
	public OrderService(UserService userService, OrderRepository orderRepository, OrderConverter orderConverter,
			OrderDTOConverter orderDTOConverter, DeliveryConverter deliveryConverter, PaymentConverter paymentConverter,
			PizzaComponentService pizzaComponentService) {
		super();
		this.userService = userService;
		this.orderRepository = orderRepository;
		this.orderConverter = orderConverter;
		this.orderDTOConverter = orderDTOConverter;
		this.deliveryConverter = deliveryConverter;
		this.paymentConverter = paymentConverter;
		this.pizzaComponentService = pizzaComponentService;
	}

	@Transactional
	public OrderDTO saveOrderWithUserId(OrderDTO orderPizza, Long userId) {
		UserEntity user = userService.getUserEntityById(userId);
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

	public OrderDTO getOrderByIdAndUserId(Long orderId, Long userId) {
		OrderEntity order = getOrderEntityByIdAndUserId(orderId, userId);

		return orderDTOConverter
				.convert(order);
	}
	
	private OrderEntity getOrderEntityByIdAndUserId(Long orderId, Long userId) {
		OrderEntity order = orderRepository.findById(orderId)
				.orElseThrow(()->new EntityNotFoundException(orderId));
		
		if (!order.getUser().getId().equals(userId)) {
			throw new EntityNotFoundException(userId);
		}

		return order;
	}
	
	@Transactional
	public OrderDTO saveDeliveryAndPaymentOfOrderOfUserId(Long orderId,Long userId, OrderDTO order){
		//Valido datos de integridad de la orden
		OrderEntity orderRetrived = getOrderEntityByIdAndUserId(orderId,userId);
		
		
		//Seteo los datos del delivery y pago
		orderRetrived.setDelivery(deliveryConverter.convert(order.getDelivery()));
		orderRetrived.setPayment(paymentConverter.convert(order.getPayment()));
		orderRetrived.getPayment().setTotalPaid(orderRetrived.getTotalPrice());
	
		
		return orderDTOConverter.convert(orderRepository.save(orderRetrived));		
	}

}
