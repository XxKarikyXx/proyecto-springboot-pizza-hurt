package org.edu.uy.proyectospring.models;

import java.util.Date;

import org.hibernate.validator.constraints.CreditCardNumber;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardDTO {	
	Long id;
	
	@NotEmpty
	private String bank;
	
	@NotNull
	private Date validUntil;
	
	//Nro válido : 4242424242424242
	@CreditCardNumber(message="El nro de tarjeta no es válido")
	private String cardNumber;
	
	@NotEmpty
	@Digits(integer=3, fraction=0, message="Código CVV inválido")
	private int cvv;

}
