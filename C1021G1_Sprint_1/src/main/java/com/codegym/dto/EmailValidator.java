package com.codegym.dto;

import com.codegym.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<EmailExistUpdate, String> {
    @Autowired
    ICustomerService customerService;


    @Override
    public void initialize(EmailExistUpdate constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return false;
    }
}
