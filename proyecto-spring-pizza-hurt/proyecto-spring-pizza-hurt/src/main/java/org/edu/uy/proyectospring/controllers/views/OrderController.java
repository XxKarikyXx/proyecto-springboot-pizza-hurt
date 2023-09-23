package org.edu.uy.proyectospring.controllers.views;

import java.util.ArrayList;
import java.util.List;

import org.edu.uy.proyectospring.entities.PizzaComponent;
import org.edu.uy.proyectospring.entities.PizzaComponentEnum;
import org.edu.uy.proyectospring.models.OrderDTO;
import org.edu.uy.proyectospring.services.OrderService;
import org.edu.uy.proyectospring.services.PizzaComponentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
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
public class OrderController {
	
	OrderService orderService;
	
	PizzaComponentService pizzaComponentService;

	public OrderController(OrderService orderService, PizzaComponentService pizzaComponentService) {
		super();
		this.orderService = orderService;
		this.pizzaComponentService = pizzaComponentService;
	}
	
	@GetMapping()
	public String showCartForm() {
		return "cart";
	}
	

	@PostMapping
	public String processOrder(@Valid OrderDTO orderPizza, BindingResult errors, SessionStatus sessionStatus) {
		
	if (errors.hasErrors()) {
		return "cart";
	}
	
	//orderService.save(orderDTO);
	
	sessionStatus.setComplete();
	return "redirect:/";		
	}
	
	@PostMapping("/delete")
	public String deleteCart(SessionStatus sessionStatus) {	
		sessionStatus.setComplete();
		return "redirect:/carrito";
	}
	
	@ModelAttribute(name = "orderPizza")
	public OrderDTO orderPizza() {
		return new OrderDTO();
	}
	
	
	//Get ingredients
	//Refactorear porque hay repeticion
	@ModelAttribute	
	public void addComponentsToModel1(Model model) {	
		List<PizzaComponent> components = pizzaComponentService.getAllComponents();
		PizzaComponentEnum enumPizza = null;
		
		List<PizzaComponent> componentsToAddToModel = new ArrayList<PizzaComponent>();
		
		for (int i = 0; i < components.size(); i++) {
			
			if (enumPizza != components.get(i).getComponentType()) {				
				
				if (i != 0) {
					model.addAttribute(enumPizza.toString().toLowerCase(), new ArrayList<PizzaComponent>(componentsToAddToModel));
					componentsToAddToModel.clear();
				}			
				enumPizza = components.get(i).getComponentType();
			}
			
			componentsToAddToModel.add(components.get(i));
		}		
		model.addAttribute(enumPizza.toString().toLowerCase(), new ArrayList<PizzaComponent>(componentsToAddToModel));
	}
	


}
