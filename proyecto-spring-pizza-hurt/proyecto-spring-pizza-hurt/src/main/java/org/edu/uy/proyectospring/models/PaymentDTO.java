package org.edu.uy.proyectospring.models;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentDTO {
	
	private Long id;
	
	@Valid
	private CardDTO card;
}
