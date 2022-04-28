package com.codegym.model;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class SignForm implements Validator {
    @Email
    @NotBlank
    private String email;
    @Size(min = 8,max = 20)
    @NotBlank
    @Pattern(regexp = "[0-9]{9}")
    private String password;
    private String confirmPassword;
    @Pattern(regexp = "(\\+849|09)(1|2|5|7|)[0-9]{7}")
    private String phone;
    @Size(min = 10, max = 50)
    private String fullName;
    private String birthday;
    private String address;
    private Boolean gender;
    @Pattern(regexp = "[0-9]{12}")
    private String idCard;
    private Countries country;

    public SignForm() {
    }

    public SignForm(String email, String password, String confirmPassword, String phone,
                    String fullName, String birthday,
                    String address, Boolean gender, String idCard, Countries country) {

        this.email = email;

        this.password = password;
        this.confirmPassword = confirmPassword;
        this.phone = phone;
        this.fullName = fullName;
        this.birthday = birthday;
        this.address = address;
        this.gender = gender;
        this.idCard = idCard;
        this.country = country;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public Countries getCountry() {
        return country;
    }

    public void setCountry(Countries country) {
        this.country = country;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        SignForm signForm = (SignForm) target;
        if(!signForm.getConfirmPassword().equals(signForm.getPassword())){
            errors.rejectValue("confirmPassword","","Phải giống với mật khẩu");
        }
    }
}
