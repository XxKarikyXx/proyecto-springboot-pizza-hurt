package org.edu.uy.proyectospring.models;

import java.util.Date;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardDTO {	
	
	@Min(value=1L, groups = PaymentInfo.class, message="Debe elegir una forma de pago válida")
	Long id;
	
	@NotEmpty(message="Debe ingresar una institución bancaria válida")
	private String bank;
	
	@Future(message="La tarjeta está vencida")
	@NotNull(message="Debe ingresar una fecha de vencimiento válida")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date validUntil;
	
	//Nro válido : 4242424242424242
	@CreditCardNumber(message="El nro de tarjeta no es válido")
	private String cardNumber;
	
	@NotNull(message="El campo CVV no puede estar vacío")
	@Min(value=100, message="El CVV debe ser un número de 3 dígitos")
	@Max(value=999, message="El CVV debe ser un número de 3 dígitos")
	private int cvv;
}
