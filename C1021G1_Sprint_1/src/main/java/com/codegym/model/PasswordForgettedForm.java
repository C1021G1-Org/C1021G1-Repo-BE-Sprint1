package com.codegym.model;

import com.codegym.service.sercurity.ICheckingForgetedPsForm;
import com.codegym.service.sercurity.ICheckingSignUpForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.NotBlank;

public class PasswordForgettedForm  implements Validator {
    @Autowired
    ICheckingForgetedPsForm checkingForgetedPsForm;
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String confirmPassword;

    public PasswordForgettedForm() {
    }

    public PasswordForgettedForm(String email, String password, String confirmPassword) {
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        PasswordForgettedForm signForm = (PasswordForgettedForm) target;
        String errorPs = checkingForgetedPsForm.checkPs(signForm);
        if(errorPs!=""){
            errors.rejectValue("passError", "", "Mật khẩu không đúng định dạng");
        }

        //confirmPs equal ps
        if (!signForm.getConfirmPassword().equals(signForm.getPassword())) {
            errors.rejectValue("confirmPassword", "", "Phải giống với mật khẩu");
        }

    }
}
