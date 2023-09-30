package org.edu.uy.proyectospring.converters;

import org.edu.uy.proyectospring.entities.Pizza;
import org.edu.uy.proyectospring.models.PizzaDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PizzaComponentDTOConverter implements Converter<Pizza, PizzaDTO>{

	@Override
	public PizzaDTO convert(Pizza source) {
		PizzaDTO mappedPizza = new PizzaDTO();
		mappedPizza.setName(source.getName());
		mappedPizza.setMass(source.getMass().getId());
		mappedPizza.setSize(source.getSize().getId());
		mappedPizza.setCheese(source.getCheese().getId());
		mappedPizza.setTopping(source.getTopping().getId());
		mappedPizza.setSauce(source.getSauce().getId());
		return mappedPizza;
	}

}
