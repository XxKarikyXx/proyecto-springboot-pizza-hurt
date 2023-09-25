package org.edu.uy.proyectospring.controllers.views;

import org.edu.uy.proyectospring.models.OrderDTO;
import org.edu.uy.proyectospring.services.OrderService;
import org.edu.uy.proyectospring.services.PizzaComponentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import jakarta.validation.Valid;

@RequestMapping("/carrito")
@Controller
@SessionAttributes("orderPizza")
public class CartController {
	OrderService orderService;
	
	PizzaComponentService pizzaComponentService;

	public CartController(OrderService orderService, PizzaComponentService pizzaComponentService) {
		super();
		this.orderService = orderService;
		this.pizzaComponentService = pizzaComponentService;
	}
	
	@GetMapping()
	public String showCartForm() {
		return "cart";
	}
	

	@PostMapping
	public String processOrder(@ModelAttribute(name="orderPizza") @Valid OrderDTO orderPizza, BindingResult bindingResult, SessionStatus sessionStatus) {		
		
		if (bindingResult.hasErrors()) {
			System.out.println(bindingResult.getAllErrors().get(0));
			System.out.println("aaaaaa");
			return "cart";
		}
		try {
			orderService.saveOrderWithUserId(orderPizza,1L);
		}catch(Exception ex) {
			System.out.println(ex);
			bindingResult.reject("errorCode", "Hubo una inconsistencia en algunos de los datos ingresados..., se sugiere refrescar y volver a intentar");
			return "cart";
		}
		sessionStatus.setComplete();
		return "redirect:/ordenes";		
	}
	
	@PostMapping("/eliminar")
	public String deleteCart(SessionStatus sessionStatus) {	
		sessionStatus.setComplete();
		return "redirect:/carrito";
	}
	
	@PostMapping("/pizza/eliminar")
	public String removePizzaFromCart(int number,@ModelAttribute(name="orderPizza") OrderDTO orderPizza) {
		orderPizza.removePizzaByIndex(number);
		return "cart";
	}
	
	@ModelAttribute(name = "orderPizza")
	public OrderDTO orderPizza() {
		return new OrderDTO();
	}
	
	@ModelAttribute	
	public void addComponentsToModel1(Model model) {	
		ControllerUtilities.loadPizzaComponentsToModel(model, pizzaComponentService);
	}
	


}
