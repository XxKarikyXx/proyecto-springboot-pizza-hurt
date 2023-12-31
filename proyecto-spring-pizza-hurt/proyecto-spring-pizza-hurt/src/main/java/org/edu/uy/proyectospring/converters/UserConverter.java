package org.edu.uy.proyectospring.converters;

import org.edu.uy.proyectospring.entities.UserEntity;
import org.edu.uy.proyectospring.models.UserDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserConverter implements Converter<UserDTO, UserEntity>{

	@Override
	public UserEntity convert(UserDTO source) {
		UserEntity mappedEntity = new UserEntity();
		mappedEntity.setEmail(source.email());
		mappedEntity.setFullName(source.fullName());
		mappedEntity.setPassword(source.password());
		mappedEntity.setTelephone(source.telephone());
		if (source.active().isPresent()) {
			mappedEntity.setActive(source.active().get());
		}else {
			mappedEntity.setActive(true);
		}
		return mappedEntity;
	}
}
