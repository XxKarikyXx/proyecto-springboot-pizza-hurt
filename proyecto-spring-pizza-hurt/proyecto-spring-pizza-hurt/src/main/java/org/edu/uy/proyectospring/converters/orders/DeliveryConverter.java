package org.edu.uy.proyectospring.converters.orders;

import org.edu.uy.proyectospring.entities.Address;
import org.edu.uy.proyectospring.entities.Delivery;
import org.edu.uy.proyectospring.models.DeliveryDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DeliveryConverter implements Converter<DeliveryDTO, Delivery>{

	
	public Delivery convert(DeliveryDTO source) {
		
		Delivery delivery = new Delivery();
		delivery.setObservations(source.getObservations());
		
		Address address = new Address();
		address.setId(0L);
		address.setApartmentNumber(source.getAddress().getApartmentNumber());
		address.setDistrict(source.getAddress().getDistrict());
		address.setDoorNumber(source.getAddress().getDoorNumber());
		address.setStreet(source.getAddress().getStreet());
		address.setZipCode(source.getAddress().getZipCode());
		delivery.setAddress(address);

		return delivery;
	}

}
