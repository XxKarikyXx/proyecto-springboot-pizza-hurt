package org.edu.uy.proyectospring.entities;

import java.util.Date;

import org.hibernate.validator.constraints.CreditCardNumber;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
public class Card extends BaseEntity{
	
	@NotNull(message="Debe ingresar una institución bancaria válida")
	@NotEmpty(message="Debe ingresar una institución bancaria válida")
	private String bank;
	
	@NotNull(message="Debe ingresar una fecha de vencimiento válida")
	private Date validUntil;
	
	//Nro válido : 4242424242424242
	@CreditCardNumber(message="El nro de tarjeta no es válido")
	private String cardNumber;
	
	@NotNull(message="El campo CVV no puede estar vacío")
	@Min(value=100, message="El CVV debe ser un número de 3 dígitos")
	@Max(value=999, message="El CVV debe ser un número de 3 dígitos")
	private int cvv;

	public Card(String bank, Date validUntil, String cardNumber, int cvv) {
		super();
		this.bank = bank;
		this.validUntil = validUntil;
		this.cardNumber = cardNumber;
		this.cvv = cvv;
	}
	
}
