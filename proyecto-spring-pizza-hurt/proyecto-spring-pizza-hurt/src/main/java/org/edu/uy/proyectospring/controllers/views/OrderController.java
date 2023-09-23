package org.edu.uy.proyectospring.controllers.views;

import java.util.List;

import org.edu.uy.proyectospring.entities.Cheese;
import org.edu.uy.proyectospring.entities.Mass;
import org.edu.uy.proyectospring.entities.OrderEntity;
import org.edu.uy.proyectospring.entities.Pizza;
import org.edu.uy.proyectospring.entities.PizzaComponent;
import org.edu.uy.proyectospring.entities.Sauce;
import org.edu.uy.proyectospring.entities.Size;
import org.edu.uy.proyectospring.entities.Topping;
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

@RequestMapping("/ordenes")
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
	
	
	//Get ingredients
	@ModelAttribute	
	public void addComponentsToModel(Model model) {		
		List<Mass>  masses = pizzaComponentService.getMasses();
		model.addAttribute("mass", masses);
		
		List<Cheese>  cheeses = pizzaComponentService.getCheeses();
		model.addAttribute("cheese", cheeses);
		
		List<Sauce>  sauces = pizzaComponentService.getSauces();
		model.addAttribute("sauce", sauces);
		
		List<Size>  sizes = pizzaComponentService.getSizes();
		model.addAttribute("size", sizes);
		
		List<Topping>  toppings = pizzaComponentService.getToppings();
		model.addAttribute("topping", toppings);
	}
	
	@ModelAttribute(name = "orderPizza")
	public OrderDTO orderPizza() {
		return new OrderDTO();
	}
	
	/*
	@ModelAttribute(name = "pizza")
	public PizzaDTO pizza() {
		return new PizzaDTO();
	}
	*/
	
	@PostMapping("/pizza")
	//En el ejemplo no necesita pasarle el @ModelAttribute pero si no lo hago no muestra los errores...
	//Lo dejo marcado para ver que ocurre...
	public String addPizzaToOrder(boolean sameForm,@ModelAttribute("pizza") @Valid PizzaDTO pizza, BindingResult bindingResult, @ModelAttribute OrderDTO orderPizza) {
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
	
	
	@GetMapping("/pizza")
	public String showCreatePizzaForm() {
		return "createPizza";
	}
	
	

}
