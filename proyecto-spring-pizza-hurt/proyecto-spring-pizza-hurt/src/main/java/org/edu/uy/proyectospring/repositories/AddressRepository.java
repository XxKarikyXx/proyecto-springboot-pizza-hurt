package org.edu.uy.proyectospring.repositories;

import org.edu.uy.proyectospring.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AddressRepository extends JpaRepository<Address, Long>{

}
