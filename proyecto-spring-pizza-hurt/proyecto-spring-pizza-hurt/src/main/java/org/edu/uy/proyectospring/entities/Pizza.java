package org.edu.uy.proyectospring.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class Pizza extends BaseEntity{
	
	@NotNull
	@NotEmpty
	private String name;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="size_id")
	private Size size;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="mass_id")
	private Mass mass;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="sauce_id")
	private Sauce sauce;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="cheese_id")
	private Cheese cheese;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="topping_id")
	private Topping topping;
	
	@NotEmpty
	private int quantity;
	

	public Pizza(String name, Size size, Mass mass, Sauce sauce, Cheese cheese, Topping topping, int quantity) {
		super();
		this.name = name;
		this.size = size;
		this.mass = mass;
		this.sauce = sauce;
		this.cheese = cheese;
		this.topping = topping;
		this.quantity = quantity;
	}

}
