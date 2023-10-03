package org.edu.uy.proyectospring.converters;


import org.edu.uy.proyectospring.entities.Card;
import org.edu.uy.proyectospring.models.CardDTO;
import org.edu.uy.proyectospring.repositories.CardRepository;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CardDTOConverter implements Converter<Card, CardDTO>{

	CardRepository cardRepository;
	
	
	
	public CardDTOConverter(CardRepository cardRepository) {
		super();
		this.cardRepository = cardRepository;
	}


	@Override
	public CardDTO convert(Card source) {
		CardDTO cardDTO  = new CardDTO();
		cardDTO.setBank(source.getBank());
		cardDTO.setCardNumber(source.getCardNumber());
		cardDTO.setCvv(source.getCvv());
		cardDTO.setValidUntil(source.getValidUntil());
		cardDTO.setId(source.getId());
		
		return cardDTO;
	}

}
