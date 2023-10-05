package org.edu.uy.proyectospring.services;

import org.edu.uy.proyectospring.converters.UserConverter;
import org.edu.uy.proyectospring.converters.UserDTOConverter;
import org.edu.uy.proyectospring.entities.UserEntity;
import org.edu.uy.proyectospring.models.UserDTO;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {
	
	private final AuthenticationProvider authenticationProvider;
	
	private final UserConverter userConverter;
	
	private final UserDTOConverter userDTOConverter;
	
	private final AuthenticationContext autentication;

	
	public AuthorizationService(AuthenticationContext autentication, UserConverter userConverter, UserDTOConverter userDTOConverter,
			AuthenticationProvider authenticationProvider) {
		super();
		this.autentication = autentication;
		this.userConverter = userConverter;
		this.userDTOConverter = userDTOConverter;
		this.authenticationProvider = authenticationProvider;
	}

	
	public UserEntity getUserLogged() {

		UserEntity user =  (UserEntity) autentication.getAuthentication().getPrincipal();
		if (user == null || user.getId() == 0) {
			throw new RuntimeException("No se pudo obtener la información del usuario autenticado");
		}
		return user;
	}
	
	public UserDTO getUserDTOLogged() {
		return userDTOConverter.convert(getUserLogged());
	}
	
	//Funciona pero luego de la request es como que pierde el contexto
	public void authenticateUser(UserEntity user) {
	    UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
	    Authentication auth = authenticationProvider.authenticate(authReq);	    
	  
	    SecurityContext sc = SecurityContextHolder.getContext();
	    sc.setAuthentication(auth);	      
	}
	
	public void authenticateUser(UserDTO userDTO) {
		UserEntity user = userConverter.convert(userDTO);
		authenticateUser(user);
	}
}
