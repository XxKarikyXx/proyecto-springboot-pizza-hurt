package org.edu.uy.proyectospring.models;

import org.edu.uy.proyectospring.entities.constraints.EmailConstraint;

import jakarta.validation.constraints.NotEmpty;

public class LogInDTO {

	@NotEmpty
	@EmailConstraint	
	private String email;

	@NotEmpty
	private String password;
	

	public LogInDTO(@NotEmpty String email, @NotEmpty String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
}
