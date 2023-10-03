package org.edu.uy.proyectospring.converters;

import java.util.stream.Collectors;

import org.edu.uy.proyectospring.entities.OrderEntity;
import org.edu.uy.proyectospring.models.OrderDTO;
import org.edu.uy.proyectospring.repositories.CardRepository;
import org.edu.uy.proyectospring.repositories.PaymentRepository;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class OrderConverter implements Converter<OrderDTO, OrderEntity>{
	
	PizzaComponentConverter pizzaComponentConverter;
	
	PaymentConverter paymentConverter;
	
	DeliveryConverter deliveryConverter;
	
	CardRepository cardRepository;
	
	PaymentRepository paymentRepository;

	UserConverter userConverter;

	

	public OrderConverter(PizzaComponentConverter pizzaComponentConverter, PaymentConverter paymentConverter,
			DeliveryConverter deliveryConverter, CardRepository cardRepository, PaymentRepository paymentRepository,
			UserConverter userConverter) {
		super();
		this.pizzaComponentConverter = pizzaComponentConverter;
		this.paymentConverter = paymentConverter;
		this.deliveryConverter = deliveryConverter;
		this.cardRepository = cardRepository;
		this.paymentRepository = paymentRepository;
		this.userConverter = userConverter;
	}

	@Override
	public OrderEntity convert(OrderDTO source) {
		OrderEntity order = new OrderEntity();
		
		if(source.getUser() != null) {
			order.setUser(userConverter.convert(source.getUser()));
		}
		
		if(source.getDelivery() != null) {
			order.setDelivery(deliveryConverter.convert(source.getDelivery()));
		}
		if(source.getPayment() != null) {
			order.setPayment(paymentConverter.convert(source.getPayment()));
		}
		if (source.getTotal() != null) {
			order.setTotalPrice(source.getTotal());
		}else {
			order.setTotalPrice(0.0);
		}

		order.setPizzas(source.getPizzas().stream()
				.map(p -> pizzaComponentConverter.convert(p))
				.collect(Collectors.toList()));
		return order;
	}

}
