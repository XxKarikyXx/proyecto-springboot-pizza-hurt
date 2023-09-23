package org.edu.uy.proyectospring.controllers.views;

import java.util.ArrayList;
import java.util.List;

import org.edu.uy.proyectospring.entities.PizzaComponent;
import org.edu.uy.proyectospring.entities.PizzaComponentEnum;
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
	
	OrderService orderService;
	
	PizzaComponentService pizzaComponentService;

	public PizzaController(PizzaComponentService pizzaComponentService) {
		super();
		this.pizzaComponentService = pizzaComponentService;
	}
	
	
	//Get ingredients
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
			System.out.println("errores" +bindingResult.getAllErrors());
			return "createPizza";
		}
		
		//Se validan los componentes antes de agregarlos. Aqui tengo que hacer un catch para emitir un error global...
		pizzaComponentService.map(pizza);
		orderPizza.addPizza(pizza);
		
		if (sameForm) {
			return "redirect:/ordenes/pizza";
		}else{
			return "redirect:/carrito";
		}
	}
	
	
	@GetMapping
	public String showCreatePizzaForm() {
		return "createPizza";
	}
	
	

}
