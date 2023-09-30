package org.edu.uy.proyectospring.converters;

import org.edu.uy.proyectospring.entities.UserEntity;
import org.edu.uy.proyectospring.models.UserDTO;
import org.springframework.core.convert.converter.Converter;

public class UserConverter implements Converter<UserDTO, UserEntity>{

	@Override
	public UserEntity convert(UserDTO source) {
		UserEntity mappedEntity = new UserEntity();
		mappedEntity.setEmail(source.email());
		mappedEntity.setFullName(source.fullName());
		mappedEntity.setPassword(source.password());
		mappedEntity.setTelephone(source.telephone());
		return mappedEntity;
	}
}
