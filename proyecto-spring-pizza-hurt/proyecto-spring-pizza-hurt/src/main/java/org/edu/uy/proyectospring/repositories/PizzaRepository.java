package org.edu.uy.proyectospring.repositories;

import org.edu.uy.proyectospring.entities.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepository extends JpaRepository<Pizza, Long>{

}