package org.edu.uy.proyectospring.converters;

import org.edu.uy.proyectospring.entities.Card;
import org.edu.uy.proyectospring.models.CardDTO;
import org.springframework.core.convert.converter.Converter;

public class CardConverter implements Converter<CardDTO, Card> {

	@Override
	public Card convert(CardDTO source)
	{
		Card mappedEntity = new Card();
		mappedEntity.setBank(source.getBank());
		mappedEntity.setValidUntil(source.getValidUntil());
		mappedEntity.setCardNumber(source.getCardNumber());
		mappedEntity.setCvv(source.getCvv());
		return mappedEntity;
	}
	
}

/*
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
		return mappedEntity;
	}
}
*/