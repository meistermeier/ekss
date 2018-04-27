package com.meistermeier.mymdb.validation;

import java.util.Locale;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.thymeleaf.util.StringUtils;

public class NotMatrixValidator implements ConstraintValidator<NotMatrix, String> {
	public void initialize(NotMatrix constraint) {
	}

	public boolean isValid(String obj, ConstraintValidatorContext context) {
		return !StringUtils.containsIgnoreCase(obj, "matrix", Locale.GERMAN);
	}
}
