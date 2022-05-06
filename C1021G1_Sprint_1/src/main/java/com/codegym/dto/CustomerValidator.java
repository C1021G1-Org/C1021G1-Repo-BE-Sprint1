package com.codegym.dto;

import com.codegym.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CustomerValidator implements ConstraintValidator<ValidatorCustomer, String> {
    @Autowired
    private ICustomerService iCustomerService;



    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {


        if (iCustomerService.finByIdCard(value) > 0) {
            System.out.println(iCustomerService.finByIdCard(value));
            return false;
        }
        if (iCustomerService.finByEmail(value) > 0) {
            System.out.println(iCustomerService.finByEmail(value));
            return false;
        }
        if (iCustomerService.finByPhone(value) > 0) {
            System.out.println(iCustomerService.finByPhone(value));
            return false;
        }
        return true;
    }

}
