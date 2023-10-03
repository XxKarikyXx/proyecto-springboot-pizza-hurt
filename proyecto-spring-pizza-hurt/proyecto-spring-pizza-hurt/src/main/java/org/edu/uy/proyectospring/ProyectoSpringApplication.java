package org.edu.uy.proyectospring;

import org.edu.uy.proyectospring.repositories.CardRepository;
import org.edu.uy.proyectospring.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.List;

import org.edu.uy.proyectospring.entities.Card;
import org.edu.uy.proyectospring.entities.UserEntity;

@SpringBootApplication
public class ProyectoSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoSpringApplication.class, args);
	}

	@Bean
	CommandLineRunner dataLoader(UserRepository userRepository, PasswordEncoder encoder, CardRepository cardRepository)
	{
		return args -> {
				UserEntity user = new UserEntity();
				user.setId(1L);
				user.setPassword(encoder.encode("1234"));
				
				user.setFullName("Pepe");
				user.setEmail("pepe@pepe.com");
				user.setActive(true);
				user.setTelephone("555 1234");
				
				Card card = new Card();
				card.setBank("Sanater");
				card.setCvv(123);
				card.setValidUntil(new Date());
				card.setCardNumber("4242424242424242");
				
				user.setCards(List.of(card));
				
				cardRepository.save(card);
				userRepository.save(user);
		};
	}
	
}
