package org.edu.uy.proyectospring.models;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeliveryDTO {
	
	Long id;
	
	@Valid
	@NotNull(groups = DeliveryInfo.class)
	private AddressDTO address;
	
	private String observations;
}
