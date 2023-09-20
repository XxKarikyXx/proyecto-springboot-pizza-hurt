package org.edu.uy.proyectospring.services;

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
	
	
}
