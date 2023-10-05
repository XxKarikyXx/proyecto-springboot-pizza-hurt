package org.edu.uy.proyectospring.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

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
                    //si seteamos esto nos obliga a usar el post de SS
                	//.formLogin(login -> login.loginPage("/login"))
                    		//.usernameParameter("email")
                    		//.passwordParameter("password")
                    		//.defaultSuccessUrl("/profile"))              
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
	

}
