package org.edu.uy.proyectospring.models;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentDTO {
	
	private Long id;
	
	@NotNull(groups = PaymentInfo.class, message="Debe elegir una forma de pago válida")
	private CardDTO card;
}
