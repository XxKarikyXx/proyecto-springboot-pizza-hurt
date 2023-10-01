package org.edu.uy.proyectospring.converters;

import org.edu.uy.proyectospring.entities.Delivery;
import org.edu.uy.proyectospring.models.AddressDTO;
import org.edu.uy.proyectospring.models.DeliveryDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DeliveryDTOConverter implements Converter<Delivery,DeliveryDTO>{

	@Override
	public DeliveryDTO convert(Delivery source) {
		DeliveryDTO delivery = new DeliveryDTO();
		delivery.setObservations(source.getObservations());
		
		
		AddressDTO address = new AddressDTO();
		address.setId(source.getId());
		address.setApartmentNumber(source.getAddress().getApartmentNumber());
		address.setDistrict(source.getAddress().getDistrict());
		address.setDoorNumber(source.getAddress().getDoorNumber());
		address.setStreet(source.getAddress().getStreet());
		address.setZipCode(source.getAddress().getZipCode());
		delivery.setAddress(address);
		
		return delivery;
	}

}
