package org.edu.uy.proyectospring.entities;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class Cheese extends PizzaComponent{

	public Cheese(@NotNull @NotEmpty String name, double price) {
		super(name, price);
		// TODO Auto-generated constructor stub
	}

	@Override
	public PizzaComponentEnum getComponentType() {
		return PizzaComponentEnum.CHEESE;
	}
	
}
