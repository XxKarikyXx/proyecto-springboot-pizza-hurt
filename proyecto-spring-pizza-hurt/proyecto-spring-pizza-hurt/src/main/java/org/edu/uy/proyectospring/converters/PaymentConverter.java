package org.edu.uy.proyectospring.converters;

import org.edu.uy.proyectospring.entities.Card;
import org.edu.uy.proyectospring.entities.Payment;
import org.edu.uy.proyectospring.models.PaymentDTO;
import org.edu.uy.proyectospring.repositories.CardRepository;
import org.edu.uy.proyectospring.repositories.PaymentRepository;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PaymentConverter implements Converter<PaymentDTO, Payment>{
	
	private final PaymentRepository paymentRepository;
	
	private final CardRepository cardRepository;
	

	public PaymentConverter(PaymentRepository paymentRepository, CardRepository cardRepository) {
		super();
		this.paymentRepository = paymentRepository;
		this.cardRepository = cardRepository;
	}


	@Override
	public Payment convert(PaymentDTO source) {
		Payment payment = new Payment();
		if(payment.getId() != null) {
			payment = paymentRepository.findById(payment.getId()).orElseThrow();
		}else {
			Card card = new Card();
			card.setId(source.getCard().getId());
					if (card.getId() != 0) {
						payment.setCard(cardRepository
								.findById(card.getId())
								.orElseThrow());
					}
		}
		return payment;
	}

}
