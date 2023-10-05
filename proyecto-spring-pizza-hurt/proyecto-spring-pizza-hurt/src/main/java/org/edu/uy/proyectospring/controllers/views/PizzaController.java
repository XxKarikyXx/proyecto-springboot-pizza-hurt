package org.edu.uy.proyectospring.controllers.views;

import org.edu.uy.proyectospring.models.OrderDTO;
import org.edu.uy.proyectospring.models.PizzaDTO;
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

import jakarta.validation.Valid;

@RequestMapping("/carrito/pizza")
@Controller
@SessionAttributes("orderPizza")
public class PizzaController {
	
	private final PizzaComponentService pizzaComponentService;

	public PizzaController(PizzaComponentService pizzaComponentService) {
		super();
		this.pizzaComponentService = pizzaComponentService;
	}
	
	@ModelAttribute	
	public void addComponentsToModel(Model model) {	
		ControllerUtilities.loadPizzaComponentsToModel(model,pizzaComponentService);
	}
	
	@ModelAttribute(name = "orderPizza")
	public OrderDTO orderPizza() {
		return new OrderDTO();
	}
	
	
	//Primera vez que entra al formulario
	@ModelAttribute(name = "pizza")
	public PizzaDTO pizza() {
		return new PizzaDTO();
	}
	
	
	@PostMapping
	public String addPizzaToOrder(boolean sameForm,@ModelAttribute(name="pizza") @Valid PizzaDTO pizza, BindingResult bindingResult, @ModelAttribute(name = "orderPizza") OrderDTO orderPizza) {
		if (bindingResult.hasErrors()) {
			return "createPizza";
		}		
		try {
			pizzaComponentService.map(pizza);
		}catch(Exception ex) {
			bindingResult.reject("errorCode", "Hubo una inconsistencia en algunos de los ingredientes");
			return "createPizza";
		}
		orderPizza.addPizza(pizza);
		
		if (sameForm) {
			return "redirect:/carrito/pizza";
		}else{
			return "redirect:/carrito";
		}
	}
	
	
	@GetMapping
	public String showCreatePizzaForm() {
		return "createPizza";
	}

}
