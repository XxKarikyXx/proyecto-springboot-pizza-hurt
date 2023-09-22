package org.edu.uy.proyectospring.entities;

import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
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
