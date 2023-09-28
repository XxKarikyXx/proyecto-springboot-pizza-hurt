package org.edu.uy.proyectospring.models;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDTO {
	
	@NotEmpty
	private String street;
	
	private int doorNumber;
	
	private int apartmentNumber;
	
	@NotEmpty
	private String district;
	
	@NotEmpty
	private String zipCode;
}
