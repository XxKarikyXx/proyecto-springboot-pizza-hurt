package org.edu.uy.proyectospring.controllers.views;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.edu.uy.proyectospring.converters.OrderDTOConverter;
import org.edu.uy.proyectospring.models.CardDTO;
import org.edu.uy.proyectospring.models.DeliveryInfo;
import org.edu.uy.proyectospring.models.OrderDTO;
import org.edu.uy.proyectospring.models.PaymentInfo;
import org.edu.uy.proyectospring.services.OrderService;
import org.edu.uy.proyectospring.services.PizzaComponentService;
import org.edu.uy.proyectospring.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;



@RequestMapping("/ordenes")
@Controller
public class OrderController {
	
	OrderService orderService;
	
	PizzaComponentService pizzaComponentService;
	
	OrderDTOConverter orderDTOConverter;
	
	UserService userService;

	public OrderController(OrderService orderService, PizzaComponentService pizzaComponentService, OrderDTOConverter orderDTOConverter,
			UserService userService) {
		super();
		this.orderService = orderService;
		this.pizzaComponentService = pizzaComponentService;
		this.orderDTOConverter = orderDTOConverter;
		this.userService = userService;
	}
	
	@GetMapping()
	public String showOrderForm(Model model) {
		//HARDCODEADO USERID
		List<OrderDTO> orders = orderService.getOrdersByUserId(1L).stream()
				.map(o -> orderDTOConverter.convert(o))
				.collect(Collectors.toList());
		
		model.addAttribute("orders", orders);
		return "orders";
	}
	
	@GetMapping("/{orderId}/pagar")
	public String showOrderDeliveryPaymentForm(@PathVariable("orderId") Long orderId, Model model) {
		//HARDCODEADO USERID
		try {
			OrderDTO orderToReturn = orderService.getOrderByIdAndUserId(orderId,1L);
			model.addAttribute("order", orderToReturn);
		}catch(Exception ex) {
			return "redirect:/error";
		}
		//Hardcodeado. Hay que en realidad cargar las tarjetas del usuario loggeado
		List<CardDTO> cards = userService.getUserCardsByUserId(1L);
		model.addAttribute("cards",cards);

		return "orderDeliveryPayment";
	}
	
	@PostMapping("/{orderId}/pagar")
	public String payOrderDeliveryPayment(@PathVariable("orderId") Long orderId, 
			@ModelAttribute(name="order") @Validated({PaymentInfo.class, DeliveryInfo.class}) OrderDTO order, 
			BindingResult bindingResult, Model model) {	

		//Mantiene los atributos no modificables de la orden en el form
		try {
			OrderDTO orderToReturn = orderService.getOrderByIdAndUserId(orderId,1L);
			order.setPizzas(orderToReturn.getPizzas());
			order.setTotal(orderToReturn.getTotal());
			model.addAttribute("order", order);
		}catch(Exception ex) {
			return "redirect:/error";
		}
		
		//if (bindingResult.hasErrors()) {
			//return "orderDeliveryPayment";
		//}
		orderService.saveDeliveryAndPaymentOfOrderOfUserId(orderId, 1L, order);

		return "redirect:/ordenes";
	}
	


}
