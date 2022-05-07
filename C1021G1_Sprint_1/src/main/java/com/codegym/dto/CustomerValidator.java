package com.codegym.dto;

import com.codegym.controller.CustomerController;
import com.codegym.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CustomerValidator implements ConstraintValidator<ValidatorCustomer, String> {
    @Autowired
    private ICustomerService iCustomerService;
    @Autowired
    CustomerController customerController;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (iCustomerService.finByIdCard(value) > 0) {
            return false;
        }
        if (iCustomerService.finByEmail(value) > 0) {
            return false;
        }
        if (iCustomerService.finByPhone(value) > 0) {
            return false;
        }
        return true;
    }
}
