package org.edu.uy.proyectospring.repositories;

import org.edu.uy.proyectospring.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
	UserEntity findByEmail(String email);
}
