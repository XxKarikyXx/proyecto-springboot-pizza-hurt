package org.edu.uy.proyectospring.controllers.views;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.edu.uy.proyectospring.converters.OrderDTOConverter;
import org.edu.uy.proyectospring.models.CardDTO;
import org.edu.uy.proyectospring.models.DeliveryInfo;
import org.edu.uy.proyectospring.models.OrderDTO;
import org.edu.uy.proyectospring.models.PaymentDTO;
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
		List<OrderDTO> orders = orderService.getOrdersByUserId(userService.getUserLogged().getId()).stream()
				.map(o -> orderDTOConverter.convert(o))
				.collect(Collectors.toList());
		
		model.addAttribute("orders", orders);
		return "orders";
	}
	
	@GetMapping("/{orderId}")
	public String showOrder(@PathVariable("orderId") Long orderId, Model model) {
		try {
			OrderDTO orderToReturn = orderService.getOrderByIdAndUserId(orderId,userService.getUserLogged().getId());
			model.addAttribute("order", orderToReturn);
		}catch(Exception ex) {
			return "redirect:/error";
		}
		return "order";
	}
	
	//revisar
	@GetMapping("/{orderId}/pagar")
	public String showOrderDeliveryPaymentForm(@PathVariable("orderId") Long orderId,@ModelAttribute(name="order") OrderDTO order, Model model) {
		try {
			OrderDTO orderToReturn = orderService.getOrderByIdAndUserId(orderId,userService.getUserLogged().getId());
			
			PaymentDTO payment = new PaymentDTO();
			payment.setCard(new CardDTO());
			orderToReturn.setPayment(payment);
			
			model.addAttribute("order", orderToReturn);
		
		}catch(Exception ex) {
			System.out.println(ex);
			return "redirect:/error";
		}

		return "orderDeliveryPayment";
	}
	
	@PostMapping("/{orderId}/pagar")
	public String payOrderDeliveryPayment(@PathVariable("orderId") Long orderId, 
			@ModelAttribute(name="order") @Validated({PaymentInfo.class, DeliveryInfo.class}) OrderDTO order, 
			BindingResult bindingResult, Model model) {	

		//Mantiene los atributos no modificables de la orden en el form
		try {
			OrderDTO orderToReturn = orderService.getOrderByIdAndUserId(orderId,userService.getUserLogged().getId());
			order.setPizzas(orderToReturn.getPizzas());
			order.setTotal(orderToReturn.getTotal());
			model.addAttribute("order", order);
		}catch(Exception ex) {
			bindingResult.reject(ex.getMessage());
			return "orderDeliveryPayment";
		}
		
		if (bindingResult.hasErrors()) {
			return "orderDeliveryPayment";
		}
		orderService.saveDeliveryAndPaymentOfOrderOfUserId(orderId, userService.getUserLogged().getId(), order);

		return "redirect:/ordenes";
	}
	
	@ModelAttribute	
	public void addComponentsToModel1(Model model) {	
		List<CardDTO> cards = userService.getUserCardsByUserId(userService.getUserLogged().getId());
		model.addAttribute("cards",cards);
	}


}
