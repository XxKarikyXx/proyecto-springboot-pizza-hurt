package org.edu.uy.proyectospring.converters.orders;

import org.edu.uy.proyectospring.converters.PizzaComponentDTOConverter;
import org.edu.uy.proyectospring.entities.OrderEntity;
import org.edu.uy.proyectospring.models.orders.OrderDTO;
import org.edu.uy.proyectospring.models.orders.OrderWithIdDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class OrderWithIdDTOConverter implements Converter<OrderEntity, OrderWithIdDTO>{

	PizzaComponentDTOConverter pizzaComponentDTOConverter;
	
	OrderDTOConverter orderDTOConverter;
	
	PaymentDTOConverter paymentDTOConverter;

	public OrderWithIdDTOConverter(PizzaComponentDTOConverter pizzaComponentDTOConverter, OrderDTOConverter orderDTOConverter,
			PaymentDTOConverter paymentDTOConverter) {
		super();
		this.pizzaComponentDTOConverter = pizzaComponentDTOConverter;
		this.orderDTOConverter = orderDTOConverter;
		this.paymentDTOConverter = paymentDTOConverter;
	}

	
	@Override
	public OrderWithIdDTO convert(OrderEntity source) {
		OrderDTO order = orderDTOConverter.convert(source);
		OrderWithIdDTO finalOrder =  new OrderWithIdDTO();
		finalOrder.setPizzas(order.getPizzas());
		finalOrder.setTotal(source.getTotalPrice());
		
		//Esto tal vez cambie
		if(source.getPayment() != null) {
			finalOrder.setPayment(paymentDTOConverter.convert(source.getPayment()));
		}
		finalOrder.setId(source.getId());
		return finalOrder;		
	}

}
