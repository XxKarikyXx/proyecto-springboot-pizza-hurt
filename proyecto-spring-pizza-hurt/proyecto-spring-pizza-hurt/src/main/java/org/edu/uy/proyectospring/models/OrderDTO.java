package org.edu.uy.proyectospring.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public  class OrderDTO{
	
	private Long id;
	
	private Double total;
	
	private UserDTO user;
	
	@NotNull(groups = PaymentInfo.class)
	private PaymentDTO payment;
	
	@NotNull(groups = DeliveryInfo.class)
	private DeliveryDTO delivery;
	
	@Size(min= 1, message="No se puede generar la orden sin pizzas", groups = CommonInfo.class)
	private List<PizzaDTO> pizzas = new ArrayList<PizzaDTO>();

	public void addPizza(PizzaDTO pizza) {
		this.pizzas.add(pizza);		
	}

	public void removePizza(PizzaDTO pizza) {
		this.pizzas.remove(pizza);
		
	}

	public void removePizzaByIndex(int number) {
		if (number < this.pizzas.size() && number > -1) {
			this.pizzas.remove(number);	
		}
		
	}

	public void setPizzas(List<PizzaDTO> pizzas) {
		this.pizzas = pizzas;	
	}
	
}
