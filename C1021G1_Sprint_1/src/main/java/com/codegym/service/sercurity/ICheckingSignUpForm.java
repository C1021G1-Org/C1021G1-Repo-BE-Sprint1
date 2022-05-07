package com.codegym.service.sercurity;

import com.codegym.model.SignForm;

public interface ICheckingSignUpForm {
    String checkPs(SignForm form);
    String checkConfirmPs(SignForm form);
    String checkBirthday(SignForm form);
}
