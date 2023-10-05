package org.edu.uy.proyectospring.converters;

import java.util.stream.Collectors;

import org.edu.uy.proyectospring.entities.OrderEntity;
import org.edu.uy.proyectospring.models.OrderDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class OrderDTOConverter implements Converter<OrderEntity, OrderDTO>{
	
	private final PizzaComponentDTOConverter pizzaComponentDTOConverter;
	
	private final PaymentDTOConverter paymentDTOConverter;
	
	private final DeliveryDTOConverter deliveryDTOConverter;
	
	private final UserDTOConverter userDTOConverter;
	

	public OrderDTOConverter(PizzaComponentDTOConverter pizzaComponentDTOConverter, PaymentDTOConverter paymentDTOConverter,
			DeliveryDTOConverter deliveryDTOConverter, UserDTOConverter userDTOConverter) {
		super();
		this.pizzaComponentDTOConverter = pizzaComponentDTOConverter;
		this.paymentDTOConverter = paymentDTOConverter;
		this.deliveryDTOConverter = deliveryDTOConverter;
		this.userDTOConverter = userDTOConverter;
	}


	@Override
	public OrderDTO convert(OrderEntity source) {
		OrderDTO order = new OrderDTO();

		order.setPizzas(source.getPizzas().stream()
				.map(p -> pizzaComponentDTOConverter.convert(p))
				.collect(Collectors.toList()));
		
		if (source.getTotalPrice() != null) {
			order.setTotal(source.getTotalPrice());
		}else{
			order.setTotal(0.0);
		}
		
		order.setOrderDateTime(source.getOrderDateTime());
		
		if(source.getUser() != null)
		{
			order.setUser(userDTOConverter.convert(source.getUser()));
		}
		if(source.getPayment() != null) {
			order.setPayment(paymentDTOConverter.convert(source.getPayment()));
		}
		
		if(source.getDelivery() != null) {
			order.setDelivery(deliveryDTOConverter.convert(source.getDelivery()));
		}
		order.setId(source.getId());
		
		return order;
	}

	

}