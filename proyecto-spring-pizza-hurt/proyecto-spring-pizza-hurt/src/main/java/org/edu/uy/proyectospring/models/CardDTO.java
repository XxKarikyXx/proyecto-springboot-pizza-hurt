package org.edu.uy.proyectospring.models;

import java.util.Date;

import org.hibernate.validator.constraints.CreditCardNumber;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardDTO {	
	
	@Size(min=1,groups = PaymentInfo.class, message="Debe elegir una forma de pago válida")
	Long id;
	
	@NotEmpty(message = "La tarjeta tiene que tener un banco emisor")
	private String bank;
	
	@Future(message="La tarjeta está vencida")
	@NotNull
	private Date validUntil;
	
	//Nro válido : 4242424242424242
	@CreditCardNumber(message="El nro de tarjeta no es válido")
	private String cardNumber;
	
	@NotEmpty
	@Digits(integer=3, fraction=0, message="Código CVV inválido")
	private int cvv;

}
