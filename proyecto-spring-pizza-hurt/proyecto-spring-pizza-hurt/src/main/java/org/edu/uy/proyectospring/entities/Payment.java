package org.edu.uy.proyectospring.entities;


import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class Payment extends BaseEntity{
	
	@NotNull
	@ManyToOne
	@JoinColumn(name= "card_id")
	private Card card;
	
	@NotNull
	private double totalPaid;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date paymentDateTime;

	public Payment(Card card, double totalPaid) {
		super();
		this.card = card;
		this.totalPaid = totalPaid;
	}

}
