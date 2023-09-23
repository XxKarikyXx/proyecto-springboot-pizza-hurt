package org.edu.uy.proyectospring.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.edu.uy.proyectospring.converters.PizzaComponentConverter;
import org.edu.uy.proyectospring.entities.Cheese;
import org.edu.uy.proyectospring.entities.Mass;
import org.edu.uy.proyectospring.entities.Pizza;
import org.edu.uy.proyectospring.entities.PizzaComponent;
import org.edu.uy.proyectospring.entities.Sauce;
import org.edu.uy.proyectospring.entities.Size;
import org.edu.uy.proyectospring.entities.Topping;
import org.edu.uy.proyectospring.models.PizzaDTO;
import org.edu.uy.proyectospring.repositories.CheeseRepository;
import org.edu.uy.proyectospring.repositories.MassRepository;
import org.edu.uy.proyectospring.repositories.SauceRepository;
import org.edu.uy.proyectospring.repositories.SizeRepository;
import org.edu.uy.proyectospring.repositories.ToppingRepository;
import org.springframework.stereotype.Service;

@Service
public class PizzaComponentService {
	
	private PizzaComponentConverter pizzaComponentConverter;
	
	private MassRepository massRepository;
	
	private SauceRepository sauceRepository;
	
	private SizeRepository sizeRepository;
	
	private CheeseRepository cheeseRepository;
	
	private ToppingRepository toppingRepository;

	public PizzaComponentService(MassRepository massRepository, SauceRepository sauceRepository,
			SizeRepository sizeRepository, CheeseRepository cheeseRepository, ToppingRepository toppingRepository, PizzaComponentConverter pizzaComponentConverter) {
		super();
		this.massRepository = massRepository;
		this.sauceRepository = sauceRepository;
		this.sizeRepository = sizeRepository;
		this.cheeseRepository = cheeseRepository;
		this.toppingRepository = toppingRepository;
		this.pizzaComponentConverter = pizzaComponentConverter;
	}
	
	public List<Mass> getMasses(){
		return this.massRepository.findAll();
	}
	public List<Sauce> getSauces(){
		return this.sauceRepository.findAll();
	}
	public List<Size> getSizes(){
		return this.sizeRepository.findAll();
	}
	public List<Cheese> getCheeses(){
		return this.cheeseRepository.findAll();
	}
	public List<Topping> getToppings(){
		return this.toppingRepository.findAll();
	}
	
	public Optional<Mass> getMassById(Long id){
		return this.massRepository.findById(id);
	}
	public Optional<Sauce> getSauceById(Long id){
		return this.sauceRepository.findById(id);
	}
	public Optional<Size> getSizeById(Long id){
		return this.sizeRepository.findById(id);
	}
	public Optional<Cheese> getCheeseById(Long id){
		return this.cheeseRepository.findById(id);
	}
	public Optional<Topping> getToppingById(Long id){
		return this.getToppingById(id);
	}
	
	public Pizza map(PizzaDTO pizza) {
		return pizzaComponentConverter.convert(pizza);
	}
	
	public List<PizzaComponent> getAllComponents(){
		List<PizzaComponent> componentsList = new ArrayList<PizzaComponent>();
		componentsList.addAll(getSizes());
		componentsList.addAll(getMasses());
		componentsList.addAll(getSauces());
		componentsList.addAll(getCheeses());
		componentsList.addAll(getToppings());
		return componentsList;
	}

}
