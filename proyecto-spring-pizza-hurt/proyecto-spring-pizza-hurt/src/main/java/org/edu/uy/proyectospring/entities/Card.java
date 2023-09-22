package org.edu.uy.proyectospring.entities;

import java.util.Date;
import jakarta.persistence.Entity;
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
	
	@NotNull
	@NotEmpty
	private String cardNumber;
	
	@NotEmpty
	private int cvv;

	public Card(String bank, Date validUntil, String cardNumber, int cvv) {
		super();
		this.bank = bank;
		this.validUntil = validUntil;
		this.cardNumber = cardNumber;
		this.cvv = cvv;
	}
	
}
