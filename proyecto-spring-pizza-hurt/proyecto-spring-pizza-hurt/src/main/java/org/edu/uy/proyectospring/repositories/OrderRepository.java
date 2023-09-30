package org.edu.uy.proyectospring.repositories;

import java.util.List;

import org.edu.uy.proyectospring.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long>{

	List<OrderEntity> findByUserId(Long userId);

}
