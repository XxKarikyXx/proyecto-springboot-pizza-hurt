package org.edu.uy.proyectospring.models;

import org.edu.uy.proyectospring.entities.constraints.EmailConstraint;

import jakarta.validation.constraints.NotBlank;

public record UserDTO(Long id, String fullName, @EmailConstraint String email, @NotBlank(message="Debe ingresar una contraseña")String password, String telephone) {};