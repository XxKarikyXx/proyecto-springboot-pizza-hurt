package org.edu.uy.proyectospring.converters.orders;

import org.edu.uy.proyectospring.entities.Payment;
import org.edu.uy.proyectospring.models.PaymentDTO;
import org.edu.uy.proyectospring.repositories.PaymentRepository;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PaymentDTOConverter implements Converter<Payment, PaymentDTO>{

	PaymentRepository paymentRepository;
	
	CardDTOConverter cardDTOConverter;
	
	
	public PaymentDTOConverter(PaymentRepository paymentRepository, CardDTOConverter cardDTOConverter) {
		super();
		this.paymentRepository = paymentRepository;
		this.cardDTOConverter = cardDTOConverter;
	}


	@Override
	public PaymentDTO convert(Payment source) {
		PaymentDTO payment = new PaymentDTO();
		payment.setId(source.getId());
		payment.setCard(cardDTOConverter.convert(source.getCard()));
		return payment;		
	}

}
