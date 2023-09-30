package org.edu.uy.proyectospring;

import org.edu.uy.proyectospring.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.edu.uy.proyectospring.entities.UserEntity;

@SpringBootApplication
public class ProyectoSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoSpringApplication.class, args);
	}

	@Bean
	CommandLineRunner dataLoader(UserRepository usuarioRepo, PasswordEncoder encoder)
	{
		return args -> {
				UserEntity user = new UserEntity();
				user.setId(1L);
				user.setPassword(encoder.encode("1234"));
				
				user.setFullName("Pepe");
				user.setEmail("pepe@pepe.com");
				user.setTelephone("555 1234");
								
				usuarioRepo.save(user);
		};
	}
	
}
