package org.edu.uy.proyectospring.converters;

import java.util.stream.Collectors;

import org.edu.uy.proyectospring.entities.OrderEntity;
import org.edu.uy.proyectospring.models.OrderDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class OrderDTOConverter implements Converter<OrderEntity, OrderDTO>{
	
	PizzaComponentDTOConverter pizzaComponentDTOConverter;
	

	public OrderDTOConverter(PizzaComponentDTOConverter pizzaComponentDTOConverter) {
		super();
		this.pizzaComponentDTOConverter = pizzaComponentDTOConverter;
	}


	@Override
	public OrderDTO convert(OrderEntity source) {
		OrderDTO order = new OrderDTO();
		order.setPizzas(source.getPizzas().stream()
				.map(p -> pizzaComponentDTOConverter.convert(p))
				.collect(Collectors.toList()));
		return order;
	}

	

}