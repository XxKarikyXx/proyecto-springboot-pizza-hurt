package org.edu.uy.proyectospring.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderWithIdDTO extends OrderDTO{
	
	
	Long id;
	
	boolean paid; 
	//Aca queda a criterio nuestro. 
	//Podemos devolver un booleano para dependiendo de ello saber si esa orden se tiene que pagar o no y lo manejamos cn el framework

}
