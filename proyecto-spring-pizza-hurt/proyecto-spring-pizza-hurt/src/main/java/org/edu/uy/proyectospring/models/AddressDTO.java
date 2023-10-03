package org.edu.uy.proyectospring.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDTO {
	
	Long id;
	
	@NotEmpty(message="Debe ingresar una calle", groups = DeliveryInfo.class)
	private String street;
	
	@Min(value=1, message="Debe ingresar un número de puerta", groups = DeliveryInfo.class)
	private int doorNumber;
	
	private int apartmentNumber;
	
	@NotEmpty(message="Debe ingresar el barrio", groups = DeliveryInfo.class)
	private String district;
	
	@NotEmpty(message="Debe ingresar el código postal", groups = DeliveryInfo.class)
	//11111 es valido
	@Pattern(regexp = "^[0-9]{5}$", message ="El código postal tiene formato incorrecto", groups = DeliveryInfo.class )
	private String zipCode;
}
