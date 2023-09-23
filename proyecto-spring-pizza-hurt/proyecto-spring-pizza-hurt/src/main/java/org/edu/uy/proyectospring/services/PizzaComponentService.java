package org.edu.uy.proyectospring.services;

import java.util.List;
import java.util.Optional;

import org.edu.uy.proyectospring.entities.Cheese;
import org.edu.uy.proyectospring.entities.Mass;
import org.edu.uy.proyectospring.entities.Pizza;
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
	
	private MassRepository massRepository;
	
	private SauceRepository sauceRepository;
	
	private SizeRepository sizeRepository;
	
	private CheeseRepository cheeseRepository;
	
	private ToppingRepository toppingRepository;

	public PizzaComponentService(MassRepository massRepository, SauceRepository sauceRepository,
			SizeRepository sizeRepository, CheeseRepository cheeseRepository, ToppingRepository toppingRepository) {
		super();
		this.massRepository = massRepository;
		this.sauceRepository = sauceRepository;
		this.sizeRepository = sizeRepository;
		this.cheeseRepository = cheeseRepository;
		this.toppingRepository = toppingRepository;
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
