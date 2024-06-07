package com.example.cbrcurrencyservice.adapter.web.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CharCodeValidator.class)
public @interface CharCodeConstraint {
    String message() default "The char code must consist of three English letters";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
