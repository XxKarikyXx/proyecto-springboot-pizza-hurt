package org.edu.uy.proyectospring.repositories;

import org.edu.uy.proyectospring.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long>{

}
