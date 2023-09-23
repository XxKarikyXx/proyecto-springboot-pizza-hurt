package org.edu.uy.proyectospring.converters;

import org.edu.uy.proyectospring.entities.Pizza;
import org.edu.uy.proyectospring.models.PizzaDTO;
import org.edu.uy.proyectospring.repositories.CheeseRepository;
import org.edu.uy.proyectospring.repositories.MassRepository;
import org.edu.uy.proyectospring.repositories.SauceRepository;
import org.edu.uy.proyectospring.repositories.SizeRepository;
import org.edu.uy.proyectospring.repositories.ToppingRepository;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PizzaComponentConverter implements Converter<PizzaDTO, Pizza>{
	
	private MassRepository massRepository;
	
	private SauceRepository sauceRepository;
	
	private SizeRepository sizeRepository;
	
	private CheeseRepository cheeseRepository;
	
	private ToppingRepository toppingRepository;
	
	

	public PizzaComponentConverter(MassRepository massRepository, SauceRepository sauceRepository,
			SizeRepository sizeRepository, CheeseRepository cheeseRepository, ToppingRepository toppingRepository) {
		super();
		this.massRepository = massRepository;
		this.sauceRepository = sauceRepository;
		this.sizeRepository = sizeRepository;
		this.cheeseRepository = cheeseRepository;
		this.toppingRepository = toppingRepository;
	}



	@Override
	public Pizza convert(PizzaDTO pizza) {
		Pizza mappedPizza = new Pizza();
		mappedPizza.setName(pizza.getName());
		mappedPizza.setMass(this.massRepository.findById(pizza.getMass()).orElseThrow());
		mappedPizza.setSize(this.sizeRepository.findById(pizza.getSize()).orElseThrow());
		mappedPizza.setCheese(this.cheeseRepository.findById(pizza.getCheese()).orElseThrow());
		mappedPizza.setTopping(this.toppingRepository.findById(pizza.getTopping()).orElseThrow());
		mappedPizza.setSauce(this.sauceRepository.findById(pizza.getSauce()).orElseThrow());
		return mappedPizza;
	}

}
