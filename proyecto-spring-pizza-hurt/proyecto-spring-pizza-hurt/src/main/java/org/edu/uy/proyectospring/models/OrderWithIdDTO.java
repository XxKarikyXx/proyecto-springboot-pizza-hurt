package org.edu.uy.proyectospring.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderWithIdDTO extends OrderDTO{
	
	
	private Long id;
	
	private Double total;
	
	private Long payment; 

}
