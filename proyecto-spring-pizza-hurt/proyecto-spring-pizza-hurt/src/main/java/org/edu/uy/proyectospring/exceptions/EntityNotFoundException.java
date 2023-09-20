package org.edu.uy.proyectospring.exceptions;

public class EntityNotFoundException extends RuntimeException {
	
	public EntityNotFoundException(Long id) {
		super("No se encontró la entidad con la id:" + id);
	}
}

