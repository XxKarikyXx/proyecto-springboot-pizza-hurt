package org.edu.uy.proyectospring.converters;

import org.edu.uy.proyectospring.entities.UserEntity;
import org.edu.uy.proyectospring.models.UserRegistrationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserRegistrationConverter implements Converter<UserRegistrationDTO, UserEntity>{

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	@Override
	public UserEntity convert(UserRegistrationDTO source) {
		UserEntity mappedEntity = new UserEntity();
		mappedEntity.setEmail(source.getEmail());
		mappedEntity.setFullName(source.getFullName());
		mappedEntity.setPassword(passwordEncoder.encode(source.getPassword()));
		mappedEntity.setTelephone(source.getTelephone());
		return mappedEntity;
	}
	
}
