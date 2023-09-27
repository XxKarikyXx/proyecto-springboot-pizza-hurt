package org.edu.uy.proyectospring.exceptions;

import java.io.IOException;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@ControllerAdvice
public class ControllerExceptionHandler {
	

	@ExceptionHandler(value=RuntimeException.class)
	public String handleMyException(RuntimeException ex, HttpServletRequest request, HttpServletResponse response) throws IOException {		
		//Lo dejo comentado porque puede servir para otra cosa. Esto podria re-dirigir a una pagina en especial poniéndole ya valores con FlashMap
		/*
		RedirectView redirectwiew = new RedirectView(request.getRequestURI());
		FlashMap outputFlashMap = RequestContextUtils.getOutputFlashMap(request);
		if (outputFlashMap != null){
			outputFlashMap.put("myAttribute", true);
		}
		*/
		return "error";
	}
	
}
