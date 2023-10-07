package org.edu.uy.proyectospring.converters;

import org.edu.uy.proyectospring.entities.UserEntity;
import org.edu.uy.proyectospring.models.UserDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserDTOConverter implements Converter<UserEntity, UserDTO>{

	@Override
	public UserDTO convert(UserEntity source) {
		UserDTO mappedEntity = new UserDTO(source.getId(),source.getFullName(),source.getEmail(),source.getPassword(),source.getTelephone(), source.isActive());

		return mappedEntity;
	}
}
