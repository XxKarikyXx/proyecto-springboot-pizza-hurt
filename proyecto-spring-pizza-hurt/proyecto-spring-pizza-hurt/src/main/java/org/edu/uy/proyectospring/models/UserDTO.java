package org.edu.uy.proyectospring.models;

import org.edu.yt.proyectospring.entities.constraints.EmailConstraint;

import jakarta.validation.constraints.NotEmpty;

public record UserDTO(@NotEmpty String fullName, @EmailConstraint String email, @NotEmpty String password, @NotEmpty String telephone) {};