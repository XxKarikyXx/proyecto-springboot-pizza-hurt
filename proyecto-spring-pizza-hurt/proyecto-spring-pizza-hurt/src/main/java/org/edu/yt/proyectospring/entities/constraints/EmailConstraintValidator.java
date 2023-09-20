package org.edu.yt.proyectospring.entities.constraints;

import jakarta.validation.ConstraintValidator;
import org.apache.commons.validator.routines.EmailValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmailConstraintValidator implements ConstraintValidator<EmailConstraint, String> {

	@Override
	public void initialize(EmailConstraint constraintAnnotation) {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		return isEmailValid(email);
	}

    public static boolean isEmailValid(String email){
        EmailValidator validator = EmailValidator.getInstance();
 
        if (!validator.isValid(email)) {
            return false;
        }
        return true;
    }
}
