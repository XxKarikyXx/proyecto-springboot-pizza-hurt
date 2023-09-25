package org.edu.uy.proyectospring.converters;

import org.edu.uy.proyectospring.entities.OrderEntity;
import org.edu.uy.proyectospring.models.OrderDTO;
import org.edu.uy.proyectospring.models.OrderWithIdDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class OrderWithIdDTOConverter implements Converter<OrderEntity, OrderWithIdDTO>{

	PizzaComponentDTOConverter pizzaComponentDTOConverter;
	
	OrderDTOConverter orderDTOConverter;
	

	public OrderWithIdDTOConverter(PizzaComponentDTOConverter pizzaComponentDTOConverter, OrderDTOConverter orderDTOConverter) {
		super();
		this.pizzaComponentDTOConverter = pizzaComponentDTOConverter;
		this.orderDTOConverter = orderDTOConverter;
	}

	
	
	@Override
	public OrderWithIdDTO convert(OrderEntity source) {
		OrderDTO order = orderDTOConverter.convert(source);
		OrderWithIdDTO finalOrder =  new OrderWithIdDTO();
		finalOrder.setPizzas(order.getPizzas());
		finalOrder.setTotal(source.getTotalPrice());
		
		//Esto tal vez cambie
		if(source.getPayment() != null) {
			finalOrder.setPayment(source.getPayment().getId());
		}else{
			finalOrder.setPayment(0L);	
		}
		finalOrder.setId(source.getId());
		return finalOrder;		
	}

}
