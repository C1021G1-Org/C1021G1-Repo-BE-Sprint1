package com.codegym.service.sercurity.impl;

import com.codegym.model.PasswordForgettedForm;
import com.codegym.service.sercurity.ICheckingForgetedPsForm;

public class CheckingForgettedPsForm implements ICheckingForgetedPsForm {
    @Override
    public String checkPs(PasswordForgettedForm form) {
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
    public String checkPsWithConfirm(PasswordForgettedForm form) {
        if(form.getPassword()!= form.getConfirmPassword()){
            return "errorConfirm";
        }else  return "";
    }
}
