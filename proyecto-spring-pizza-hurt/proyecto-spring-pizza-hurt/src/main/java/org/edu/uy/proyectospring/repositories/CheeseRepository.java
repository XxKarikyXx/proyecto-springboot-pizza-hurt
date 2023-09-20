package org.edu.uy.proyectospring.repositories;

import org.edu.uy.proyectospring.entities.Cheese;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheeseRepository extends JpaRepository<Cheese, Long>{

}
