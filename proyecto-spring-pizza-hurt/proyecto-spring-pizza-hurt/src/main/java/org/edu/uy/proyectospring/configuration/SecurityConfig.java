package org.edu.uy.proyectospring.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.Header;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

import org.edu.uy.proyectospring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

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
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		return http.authorizeHttpRequests(a -> {
			try {
                	a
                	.requestMatchers("/carrito/**", "/ordenes/**", "/profile/**").authenticated()
                	.requestMatchers("/login/**","/signin/**").anonymous()
                	.requestMatchers("/","/**").permitAll()
                	.and()
                	.csrf(csrf -> csrf.ignoringRequestMatchers("/carrito/**", "/ordenes/**", "/profile/**"))
                    .formLogin(login -> login.loginPage("/login")
                            .defaultSuccessUrl("/"))
                    .logout(logout -> logout
                            .logoutSuccessUrl("/"))
                    //Necesario para acceder a la console de h2
                    .authorizeHttpRequests(auth -> auth.requestMatchers(toH2Console()).permitAll())
                    .csrf(csrf -> csrf.ignoringRequestMatchers(toH2Console()))
                    .headers(headers -> headers.frameOptions().sameOrigin());
			} catch (Exception e) {
				throw new RuntimeException("Error en filterChain");
			}
		})
        .build();
	}
	
	/*
	// Usado hasta el 20231001
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		return http.authorizeHttpRequests(a -> {
			try {
                	a
                	.requestMatchers("/carrito/*", "/ordenes/*").authenticated()
                	.requestMatchers("/","/**").permitAll()
                    .and()
                    .formLogin()
                    .and()
                    //Necesario para acceder a la console de h2
                    .authorizeHttpRequests(auth -> auth.requestMatchers(toH2Console()).permitAll())
                    .csrf().disable();
			} catch (Exception e) {
				throw new RuntimeException("Error en filterChain");
			}
		})
        .build();
	}
	*/
	
	/*
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		return http.authorizeHttpRequests(authorize -> {
			try {
                authorize
                        .requestMatchers("/carrito/**", "/ordenes/**").authenticated()
                        .requestMatchers("/", "/**").permitAll()
                        .and()                    
                        .csrf(csrf -> csrf.ignoringRequestMatchers("/carrito/**", "/ordenes/**"))
                        .formLogin()
                        .and()
                        .logout(logout -> logout
                                .logoutSuccessUrl("/"))
                        //Necesario para acceder a la console de h2
                        .authorizeHttpRequests(auth -> auth .requestMatchers(toH2Console()).permitAll())
                        .csrf(csrf -> csrf .ignoringRequestMatchers(toH2Console()))
                        .headers(headers -> headers.frameOptions().sameOrigin());
			} catch (Exception e) {
				throw new RuntimeException("Error en filterChain");
			}
		}).build();
	}
	*/
	
	/*
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
	{
		return http.authorizeHttpRequests(a -> {
			a.requestMatchers("/signin").permitAll();
			a.requestMatchers("/").permitAll();
		}).build();
	}
	*/
	
	
	/*
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		return http.authorizeHttpRequests(a -> {
			try {
                	a
                	.requestMatchers("/","/**").permitAll()
                	.requestMatchers("/carrito/*", "/ordenes/*").authenticated()
                	.requestMatchers("/templates/fragments/**", "/styles/**", "/scripts/**").permitAll()
                	.requestMatchers("/signin","/registration","/").permitAll()
                    .and()
                    .formLogin(login -> login.loginPage("/signin")
                            .defaultSuccessUrl("/"))
                    .logout(logout -> logout
                            .logoutSuccessUrl("/"))
                    //Necesario para acceder a la console de h2
                    .authorizeHttpRequests(auth -> auth.requestMatchers(toH2Console()).permitAll())
                    .csrf(csrf -> csrf .ignoringRequestMatchers(toH2Console()));
			} catch (Exception e) {
				throw new RuntimeException("Error en filterChain");
			}
		})
        .build();
	}
	*/
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
