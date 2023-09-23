package org.edu.uy.proyectospring.controllers.views;

import java.util.ArrayList;
import java.util.List;

import org.edu.uy.proyectospring.entities.PizzaComponent;
import org.edu.uy.proyectospring.entities.PizzaComponentEnum;
import org.edu.uy.proyectospring.services.PizzaComponentService;
import org.springframework.ui.Model;

public class ControllerUtilities {

	public static void loadPizzaComponentsToModel(Model model, PizzaComponentService pizzaComponentService) {
		List<PizzaComponent> components = pizzaComponentService.getAllComponents();
		PizzaComponentEnum enumPizza = null;
		
		List<PizzaComponent> componentsToAddToModel = new ArrayList<PizzaComponent>();
		
		for (int i = 0; i < components.size(); i++) {
			
			if (enumPizza != components.get(i).getComponentType()) {				
				
				if (i != 0) {
					model.addAttribute(enumPizza.toString().toLowerCase(), new ArrayList<PizzaComponent>(componentsToAddToModel));
					componentsToAddToModel.clear();
				}			
				enumPizza = components.get(i).getComponentType();
			}
			
			componentsToAddToModel.add(components.get(i));
		}		
		model.addAttribute(enumPizza.toString().toLowerCase(), new ArrayList<PizzaComponent>(componentsToAddToModel));	
	}
	 
	 

}
