package org.edu.uy.proyectospring.entities.constraints;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = EmailConstraintValidator.class)
public @interface EmailConstraint {
	
	String message() default "El email tiene formato incorrecto";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};
}
