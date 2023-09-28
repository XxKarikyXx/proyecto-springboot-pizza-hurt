package org.edu.uy.proyectospring.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeliveryDTO {
	
	@NotNull(groups = DeliveryInfo.class)
	private AddressDTO address;
	
	@NotEmpty
	private String observations;
}
