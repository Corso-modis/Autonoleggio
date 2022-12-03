package com.agg.customValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<PasswordConstraint, String> {

	@Override
	public void initialize(PasswordConstraint password) {
	}

	@Override
	public boolean isValid(String password, ConstraintValidatorContext context) {
		return password != null
				&& password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$");
	}

}
