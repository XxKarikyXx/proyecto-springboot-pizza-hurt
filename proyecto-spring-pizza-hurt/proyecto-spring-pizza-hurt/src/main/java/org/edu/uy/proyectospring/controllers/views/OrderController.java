package org.edu.uy.proyectospring.controllers.views;


import java.util.List;
import java.util.stream.Collectors;

import org.edu.uy.proyectospring.converters.orders.OrderWithIdDTOConverter;
import org.edu.uy.proyectospring.models.orders.OrderWithIdDTO;
import org.edu.uy.proyectospring.services.OrderService;
import org.edu.uy.proyectospring.services.PizzaComponentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;



@RequestMapping("/ordenes")
@Controller
public class OrderController {
	
	OrderService orderService;
	
	PizzaComponentService pizzaComponentService;
	
	OrderWithIdDTOConverter orderWithIdDTOConverter;

	public OrderController(OrderService orderService, PizzaComponentService pizzaComponentService, OrderWithIdDTOConverter orderWithIdDTOConverter) {
		super();
		this.orderService = orderService;
		this.pizzaComponentService = pizzaComponentService;
		this.orderWithIdDTOConverter = orderWithIdDTOConverter;
	}
	
	@GetMapping()
	public String showOrderForm(Model model) {
		//HARDCODEADO USERID
		List<OrderWithIdDTO> orders = orderService.getOrdersByUserId(1L).stream()
				.map(o -> orderWithIdDTOConverter.convert(o))
				.collect(Collectors.toList());
		
		model.addAttribute("orders", orders);
		return "orders";
	}
	
	@GetMapping("/{orderId}/pagar")
	public String showOrderDeliveryPaymentForm(@PathVariable("orderId") Long orderId, Model model) {
		//HARDCODEADO USERID
		OrderWithIdDTO orderToReturn = orderService.getOrderByIdAndUserId(orderId,1L);
		
		model.addAttribute("order", orderToReturn);
		return "orderDeliveryPayment";
	}
	


}
