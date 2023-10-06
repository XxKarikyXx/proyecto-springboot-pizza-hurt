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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Service
public class AuthorizationService {
	
	private final AuthenticationProvider authenticationProvider;
	
	private final UserConverter userConverter;
	
	private final UserDTOConverter userDTOConverter;
	
	BCryptPasswordEncoder passwordEncoder;

	
	public AuthorizationService(UserConverter userConverter, UserDTOConverter userDTOConverter,
			AuthenticationProvider authenticationProvider) {
		super();
		this.userConverter = userConverter;
		this.userDTOConverter = userDTOConverter;
		this.authenticationProvider = authenticationProvider;
	}

	
	public UserEntity getUserLogged() {
		UserEntity user =  (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (user == null || user.getId() == 0) {
			throw new RuntimeException("No se pudo obtener la información del usuario autenticado");
		}
		return user;
	}
	
	public UserDTO getUserDTOLogged() {
		return userDTOConverter.convert(getUserLogged());
	}
		
	public void authenticateUser(UserEntity user, HttpServletRequest request) {
	    UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
	    Authentication auth = authenticationProvider.authenticate(authReq);	    	  
	    SecurityContext sc = SecurityContextHolder.getContext();
	    sc.setAuthentication(auth);	      
	    HttpSession session = request.getSession(true);
	    session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, sc);
	}
	
	public void authenticateUser(UserDTO userDTO, HttpServletRequest request) {
		UserEntity user = userConverter.convert(userDTO);
		authenticateUser(user, request);
	}
	
}
