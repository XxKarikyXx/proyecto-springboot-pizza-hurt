package org.edu.uy.proyectospring.entities;

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
@NoArgsConstructor
@Entity
public class Address extends BaseEntity{
	
	@NotNull
	@NotEmpty
	private String street;
	
	@NotEmpty
	private int doorNumber;
	
	private int apartmentNumber;
	
	@NotNull
	@NotEmpty
	private String district;
	
	@NotNull
	@NotEmpty
	private String zipCode;
	

	public Address(String street, int doorNumber, int apartmentNumber, String district, String zipCode) {
		super();
		this.street = street;
		this.doorNumber = doorNumber;
		this.apartmentNumber = apartmentNumber;
		this.district = district;
		this.zipCode = zipCode;
	}
	
}
