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
public class Sauce extends PizzaComponent{

	public Sauce(@NotNull @NotEmpty String name, double price) {
		super(name, price);
		// TODO Auto-generated constructor stub
	}
	

}
