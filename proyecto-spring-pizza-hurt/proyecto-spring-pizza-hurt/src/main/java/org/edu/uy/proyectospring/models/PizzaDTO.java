package org.edu.uy.proyectospring.models;


import org.hibernate.validator.constraints.Range;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
public class PizzaDTO{
	@NotEmpty(message="El nombre no puede ser vacío")
	private String name;
	
	@Range(min = 1, message="Debe indicar una salsa")
	private Long sauce;
	
	@Range(min = 1, message="Debe indicar una masa") 
	private Long mass;
	
	@Range(min = 1, message="Debe indicar un queso") 
	private Long cheese;
	
	@Range(min = 1, message="Debe indicar un tamaño") 
	private Long size;
	
	@Range(min = 1, message="Debe indicar un topin") 
	private Long topping;
}
