package org.edu.uy.proyectospring.entities;

import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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
public class Delivery extends BaseEntity{
	
	@NotNull
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="adress_id")
	private Address address;
	
	private String observations;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date deliveryDateTime;
	
	@Column(columnDefinition="boolean default false")
	private boolean delivered;
	
	public Delivery(Address address, String observations, Date deliveryDateTime, boolean delivered) {
		super();
		this.address = address;
		this.observations = observations;
		this.deliveryDateTime = deliveryDateTime;
		this.delivered = delivered;
	}

}
