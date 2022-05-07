package com.codegym.service.sercurity;

import com.codegym.model.PasswordForgettedForm;

public interface ICheckingForgetedPsForm {
    String checkPs(PasswordForgettedForm form);
    String checkPsWithConfirm(PasswordForgettedForm form);
}
