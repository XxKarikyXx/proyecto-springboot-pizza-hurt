package org.edu.uy.proyectospring.converters;

import java.util.stream.Collectors;

import org.edu.uy.proyectospring.entities.OrderEntity;
import org.edu.uy.proyectospring.models.OrderDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class OrderConverter implements Converter<OrderDTO, OrderEntity>{
	
	PizzaComponentConverter pizzaComponentConverter;

	public OrderConverter(PizzaComponentConverter pizzaComponentConverter) {
		super();
		this.pizzaComponentConverter = pizzaComponentConverter;
	}


	@Override
	public OrderEntity convert(OrderDTO source) {
		OrderEntity order = new OrderEntity();
		order.setDelivery(null);
		order.setPayment(null);
		order.setTotalPrice(0.0);
		order.setUser(null);
		order.setPizzas(source.getPizzas().stream()
				.map(p -> pizzaComponentConverter.convert(p))
				.collect(Collectors.toList()));
		return order;
	}

}
