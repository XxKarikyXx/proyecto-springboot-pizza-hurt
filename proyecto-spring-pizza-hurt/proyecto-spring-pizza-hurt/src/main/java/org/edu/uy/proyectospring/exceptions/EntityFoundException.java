package org.edu.uy.proyectospring.exceptions;

public class EntityFoundException extends RuntimeException {
	
	public EntityFoundException() {
		super("Ya existe la entidad");
	}
}

