package org.edu.uy.proyectospring.models;


import org.edu.uy.proyectospring.entities.constraints.EmailConstraint;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationDTO {	
	
	@EmailConstraint
	@NotBlank(message="El email no puede ser vacío")
	private String email;
	
	@NotBlank(message="La contraseña no puede ser vacía")
	private String password;
	
	@NotBlank(message="La confirmación de la contraseña no puede ser vacía")
	private String confirmPassword;

	@NotBlank(message="El nombre no puede ser vacío")
	private String fullName;
	

	@NotBlank(message="El teléfono no puede ser vacío")
	private String telephone;
	
	@AssertTrue(message = "No coinciden los passwords ingresados")
	public boolean isValidPassword() {
		return password.equals(confirmPassword);
	}
	
}
