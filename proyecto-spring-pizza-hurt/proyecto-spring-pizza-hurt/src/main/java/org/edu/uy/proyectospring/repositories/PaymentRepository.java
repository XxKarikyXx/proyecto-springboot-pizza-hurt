package org.edu.uy.proyectospring.repositories;

import org.edu.uy.proyectospring.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long>{

}
