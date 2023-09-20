package org.edu.uy.proyectospring.entities;

import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class PizzaComponent extends BaseEntity{
	
	@NotNull
	@NotEmpty
	private String name;
	
	private double price;

	public PizzaComponent(@NotNull @NotEmpty String name, double price) {
		super();
		this.name = name;
		this.price = price;
	}
	
}
