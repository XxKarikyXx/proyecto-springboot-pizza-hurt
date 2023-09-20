package org.edu.uy.proyectospring.repositories;

import org.edu.uy.proyectospring.entities.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long>{

}