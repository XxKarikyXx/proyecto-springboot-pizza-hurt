package org.edu.uy.proyectospring.configuration;

import java.util.ArrayList;
import java.util.List;

import org.edu.uy.proyectospring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Autowired
	private UserService userService;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder ()
	{
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		
		authenticationProvider.setUserDetailsService(userService);		
		authenticationProvider.setPasswordEncoder(this.passwordEncoder());
		
		return authenticationProvider;
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
	{
		return http.authorizeHttpRequests(a -> {
			a.requestMatchers("/registration").permitAll();
		}).build();
	}
	
	/*
	public UserDetailsService userDetailsService(PasswordEncoder encoder) {
		
		final List<UserDetails> usuarios = new ArrayList<>();
		
		usuarios.add(User.builder()
				.username("guille")
				.password(encoder.encode("guille123"))
				.authorities("ROLE_USER")
				.build());
		
		return new InMemoryUserDetailsManager(usuarios);
	}
	*/
	
	
}
