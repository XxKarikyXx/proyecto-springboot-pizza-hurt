package org.edu.uy.proyectospring.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public  class OrderDTO{
	
	@Size(min= 1, message="No se puede generar la orden sin pizzas")
	public List<PizzaDTO> pizzas = new ArrayList<PizzaDTO>();

	public void addPizza(PizzaDTO pizza) {
		this.pizzas.add(pizza);		
	}
	
}
