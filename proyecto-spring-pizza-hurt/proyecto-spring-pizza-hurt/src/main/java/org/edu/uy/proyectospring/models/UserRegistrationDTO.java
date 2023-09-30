package org.edu.uy.proyectospring.models;

import java.util.List;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationDTO {
	
	@NotNull
	@NotBlank
	private String username;
	
	@NotNull
	@NotBlank
	private String password;
	
	@NotNull
	@NotBlank
	private String confirmPassword;
	
	@NotNull
	@NotBlank
	private String fullName;
	
	@NotNull
	@NotBlank
	private String email;
	
	@NotNull
	@NotBlank
	private String telephone;
	
	@AssertTrue(message = "No coinciden los passwords ingresados")
	public boolean isValidPassword() {
		return password.equals(confirmPassword);
	}
	
}
