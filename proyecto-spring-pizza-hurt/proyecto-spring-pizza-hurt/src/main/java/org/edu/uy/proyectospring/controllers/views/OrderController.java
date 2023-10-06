package org.edu.uy.proyectospring.controllers.views;

import java.util.List;
import java.util.stream.Collectors;

import org.edu.uy.proyectospring.converters.OrderDTOConverter;
import org.edu.uy.proyectospring.models.CardDTO;
import org.edu.uy.proyectospring.models.DeliveryInfo;
import org.edu.uy.proyectospring.models.OrderDTO;
import org.edu.uy.proyectospring.models.PaymentDTO;
import org.edu.uy.proyectospring.models.PaymentInfo;
import org.edu.uy.proyectospring.services.AuthorizationService;
import org.edu.uy.proyectospring.services.OrderService;
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
	
	private final OrderService orderService;
	
	private final OrderDTOConverter orderDTOConverter;
	
	private final UserService userService;
	
	private final AuthorizationService authorizationService;
	
	public OrderController(OrderService orderService, OrderDTOConverter orderDTOConverter,
			UserService userService, AuthorizationService authorizationService) {
		super();
		this.orderService = orderService;
		this.orderDTOConverter = orderDTOConverter;
		this.userService = userService;
		this.authorizationService = authorizationService;
	}
	
	@GetMapping()
	public String showOrderForm(Model model) {
		List<OrderDTO> orders = orderService.getOrdersByUserId(authorizationService.getUserLogged().getId()).stream()
				.map(o -> orderDTOConverter.convert(o))
				.collect(Collectors.toList());
		
		model.addAttribute("orders", orders);
		return "orders";
	}
	
	@GetMapping("/{orderId}")
	public String showOrder(@PathVariable("orderId") Long orderId, Model model) {
		try {
			OrderDTO orderToReturn = orderService.getOrderByIdAndUserId(orderId,authorizationService.getUserLogged().getId());
			model.addAttribute("order", orderToReturn);
		}catch(Exception ex) {
			return "redirect:/error";
		}
		return "order";
	}
	
	@GetMapping("/{orderId}/pagar")
	public String showOrderDeliveryPaymentForm(@PathVariable("orderId") Long orderId,@ModelAttribute(name="order") OrderDTO order, Model model) {
		try {
			OrderDTO orderToReturn = orderService.getOrderByIdAndUserId(orderId,authorizationService.getUserLogged().getId());
			
			PaymentDTO payment = new PaymentDTO();
			payment.setCard(new CardDTO());
			orderToReturn.setPayment(payment);
			
			model.addAttribute("order", orderToReturn);
		
		}catch(Exception ex) {
			return "redirect:/error";
		}

		return "orderDeliveryPayment";
	}
	
	@PostMapping("/{orderId}/pagar")
	public String payOrderDeliveryPayment(@PathVariable("orderId") Long orderId, 
			@ModelAttribute(name="order") @Validated({PaymentInfo.class, DeliveryInfo.class}) OrderDTO order, 
			BindingResult bindingResult, Model model) {	
		try {
			OrderDTO orderToReturn = orderService.getOrderByIdAndUserId(orderId,authorizationService.getUserLogged().getId());
			order.setPizzas(orderToReturn.getPizzas());
			order.setTotal(orderToReturn.getTotal());
			model.addAttribute("order", order);
		}catch(Exception ex) {
			return "redirect:/orderDeliveryPayment";
		}
		
		if (bindingResult.hasErrors()) {
			return "orderDeliveryPayment";
		}
		orderService.saveDeliveryAndPaymentOfOrderOfUserId(orderId, authorizationService.getUserLogged().getId(), order);

		return "redirect:/ordenes";
	}
	
	@ModelAttribute	
	public void addComponentsToModel1(Model model) {	
		List<CardDTO> cards = userService.getUserCardsByUserId(authorizationService.getUserLogged().getId());
		model.addAttribute("cards",cards);
	}


}
