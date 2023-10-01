package org.edu.uy.proyectospring.entities;

import java.util.Date;

import org.hibernate.validator.constraints.CreditCardNumber;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Digits;
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
	
	@NotNull
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
	
	@ManyToOne
	private UserEntity user;

	public Card(String bank, Date validUntil, String cardNumber, int cvv) {
		super();
		this.bank = bank;
		this.validUntil = validUntil;
		this.cardNumber = cardNumber;
		this.cvv = cvv;
	}
	
}
