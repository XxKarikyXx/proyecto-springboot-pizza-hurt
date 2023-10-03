package org.edu.uy.proyectospring.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDTO {
	
	Long id;
	
	@NotEmpty(message="Debe ingresar una calle")
	private String street;
	
	@Size(min=1, message="Debe ingresar un número de puerta")
	private int doorNumber;
	
	private int apartmentNumber;
	
	@NotEmpty(message="Debe ingresar el barrio")
	private String district;
	
	@NotEmpty(message="Debe ingresar el código postal")
	private String zipCode;
}
