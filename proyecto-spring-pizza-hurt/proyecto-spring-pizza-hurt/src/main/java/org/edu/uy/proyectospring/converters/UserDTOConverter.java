package org.edu.uy.proyectospring.converters;

import java.util.Optional;

import org.edu.uy.proyectospring.entities.UserEntity;
import org.edu.uy.proyectospring.models.UserDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserDTOConverter implements Converter<UserEntity, UserDTO>{

	@Override
	public UserDTO convert(UserEntity source) {
		UserDTO mappedEntity = new UserDTO(source.getId(),source.getFullName(),source.getEmail(),source.getPassword(),source.getTelephone(), Optional.of(source.isActive()));

		return mappedEntity;
	}
}
