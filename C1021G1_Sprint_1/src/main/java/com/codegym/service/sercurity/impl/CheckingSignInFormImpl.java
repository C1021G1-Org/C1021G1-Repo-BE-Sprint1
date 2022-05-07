package com.codegym.service.sercurity.impl;

import com.codegym.model.SignForm;
import com.codegym.service.sercurity.ICheckingSignUpForm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class CheckingSignInFormImpl implements ICheckingSignUpForm {
    @Override
    public String checkPs(SignForm form) {
        return null;
    }

    @Override
    public String checkConfirmPs(SignForm form) {
        //checkPs
        boolean check = true;
        String password = form.getPassword();
        if(password.length()<8&&password.length()>20){
            return "passError";
        }

        for(int i = 0;i<password.length();i++){
            if(Integer.parseInt(String.valueOf(password.charAt(i)))>-1){
                check = true;
                break;
            }
            check = false;
        }
        if(!check){
            return "passError";
        }
        for(int i = 0;i<password.length();i++){
            if(((Integer.parseInt(String.valueOf(password.charAt(i)))>-1)&&
                    (String.valueOf(password.charAt(i)) == String.valueOf(password.charAt(i)).toUpperCase()))){
                check = true;
                break;
            }
            check = false;
        }
        if(!check){
            return "passError";
        }

        for(int i = 0;i<password.length();i++) {
            if (((Integer.parseInt(String.valueOf(password.charAt(i))) > -1) &&
                    (String.valueOf(password.charAt(i)) == String.valueOf(password.charAt(i)).toLowerCase()))) {
                check = true;
                break;
            }
            check = false;
        }


        if(!check){
            return "passError";
        }
        return "";
    }

    @Override
    public String checkBirthday(SignForm form) {
        Long birthdayToSeconds = new Date(form.getBirthday()).getTime();
        Long currentToSeconds = new Date().getTime();
        Long between = currentToSeconds - birthdayToSeconds;
        Long age = between/(60*60*24*1000*365);
        if(age<18 || age>150){
           return "ageError";
        }
        return "";
    }
}
