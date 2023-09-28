package org.edu.uy.proyectospring.converters.orders;

import java.util.stream.Collectors;

import org.edu.uy.proyectospring.converters.PizzaComponentConverter;
import org.edu.uy.proyectospring.entities.OrderEntity;
import org.edu.uy.proyectospring.models.orders.OrderWithIdDTO;
import org.edu.uy.proyectospring.repositories.CardRepository;
import org.edu.uy.proyectospring.repositories.PaymentRepository;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class OrderWithIdDTOToOrderConverter implements Converter<OrderWithIdDTO, OrderEntity>{

	PizzaComponentConverter pizzaComponentConverter;
	
	PaymentConverter paymentConverter;
	
	DeliveryConverter deliveryConverter;
	
	CardRepository cardRepository;
	
	PaymentRepository paymentRepository;

	public OrderWithIdDTOToOrderConverter(PizzaComponentConverter pizzaComponentConverter,
			PaymentConverter paymentConverter, DeliveryConverter deliveryConverter, CardRepository cardRepository,
			PaymentRepository paymentRepository) {
		super();
		this.pizzaComponentConverter = pizzaComponentConverter;
		this.paymentConverter = paymentConverter;
		this.deliveryConverter = deliveryConverter;
		this.cardRepository = cardRepository;
		this.paymentRepository = paymentRepository;
	}


	@Override
	public OrderEntity convert(OrderWithIdDTO source) {
		OrderEntity order = new OrderEntity();
		order.setDelivery(null);
		
		if(source.getDelivery() != null) {
			order.setDelivery(deliveryConverter.convert(source.getDelivery()));
		}
		if(source.getPayment() != null) {
			order.setPayment(paymentConverter.convert(source.getPayment()));
		}
		order.setTotalPrice(source.getTotal());
		order.setUser(null);
		order.setPizzas(source.getPizzas().stream()
				.map(p -> pizzaComponentConverter.convert(p))
				.collect(Collectors.toList()));
		return order;
	}

}
