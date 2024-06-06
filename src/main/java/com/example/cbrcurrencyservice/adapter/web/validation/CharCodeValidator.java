package com.example.cbrcurrencyservice.adapter.web.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CharCodeValidator implements ConstraintValidator<CharCodeConstraint, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value.matches("^[a-zA-Z\\d]{3}$")) {
            return true;
        }
        return false;
    }
}
