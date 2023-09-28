package org.edu.uy.proyectospring.models;

import java.util.Date;

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
	
	//podemos poner el validador de 3 digitos
	@NotEmpty
	private String cardNumber;
	
	private int cvv;

}
