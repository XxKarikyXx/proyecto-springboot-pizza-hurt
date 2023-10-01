package org.edu.uy.proyectospring.models;

import org.edu.yt.proyectospring.entities.constraints.EmailConstraint;

import jakarta.validation.constraints.NotEmpty;

public record UserDTO(Long id, @NotEmpty(message="El nombre no puede ser vacío") String fullName, @EmailConstraint String email, @NotEmpty(message="La contraseña no puede ser vacía") String password, @NotEmpty(message="El teléfono no puede ser vacío") String telephone) {};