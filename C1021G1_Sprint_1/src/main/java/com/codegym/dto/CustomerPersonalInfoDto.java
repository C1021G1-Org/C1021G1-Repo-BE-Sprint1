package com.codegym.dto;

import com.codegym.model.Countries;
import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Set;
import java.util.regex.Matcher;

public class CustomerPersonalInfoDto implements Validator {
    private final String REGEX_ID_CARD = "^[0-9]{12}";
    private final String REGEX_PHONE = "^([0-9])*$";



    private Long id;

    @NotBlank
    @Length(min = 10, max = 30)
    private String nameCustomer;

    @NotNull
    private Boolean genderCustomer;

    @NotBlank
    @Pattern(regexp = "\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])")
    private String birthdayCustomer;

    @NotBlank
    @Pattern(regexp = REGEX_ID_CARD)
    private String idCardCustomer;

    @NotBlank
    private String phoneCustomer;

    @Email
    @NotBlank
    private String emailCustomer;

    @NotBlank
    private String addressCustomer;

//    @Pattern(regexp = "([^\\s]+(\\.(?i)(jpg|png))$)")
    private String imageCustomer;

    private Countries countries;

    public CustomerPersonalInfoDto() {
    }

    public CustomerPersonalInfoDto(Long id, @NotBlank String nameCustomer, @NotNull Boolean genderCustomer, @NotBlank String birthdayCustomer, @NotBlank String idCardCustomer, @NotBlank String phoneCustomer, @Email @NotBlank String emailCustomer, @NotBlank String addressCustomer, @NotBlank String imageCustomer, Countries countries) {
        this.id = id;
        this.nameCustomer = nameCustomer;
        this.genderCustomer = genderCustomer;
        this.birthdayCustomer = birthdayCustomer;
        this.idCardCustomer = idCardCustomer;
        this.phoneCustomer = phoneCustomer;
        this.emailCustomer = emailCustomer;
        this.addressCustomer = addressCustomer;
        this.imageCustomer = imageCustomer;
        this.countries = countries;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameCustomer() {
        return nameCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
    }

    public Boolean getGenderCustomer() {
        return genderCustomer;
    }

    public void setGenderCustomer(Boolean genderCustomer) {
        this.genderCustomer = genderCustomer;
    }

    public String getBirthdayCustomer() {
        return birthdayCustomer;
    }

    public void setBirthdayCustomer(String birthdayCustomer) {
        this.birthdayCustomer = birthdayCustomer;
    }

    public String getIdCardCustomer() {
        return idCardCustomer;
    }

    public void setIdCardCustomer(String idCardCustomer) {
        this.idCardCustomer = idCardCustomer;
    }

    public String getPhoneCustomer() {
        return phoneCustomer;
    }

    public void setPhoneCustomer(String phoneCustomer) {
        this.phoneCustomer = phoneCustomer;
    }

    public String getEmailCustomer() {
        return emailCustomer;
    }

    public void setEmailCustomer(String emailCustomer) {
        this.emailCustomer = emailCustomer;
    }

    public String getAddressCustomer() {
        return addressCustomer;
    }

    public void setAddressCustomer(String addressCustomer) {
        this.addressCustomer = addressCustomer;
    }

    public String getImageCustomer() {
        return imageCustomer;
    }

    public void setImageCustomer(String imageCustomer) {
        this.imageCustomer = imageCustomer;
    }

    public Countries getCountries() {
        return countries;
    }

    public void setCountries(Countries countries) {
        this.countries = countries;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
//        return CustomerPersonalInfoDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CustomerPersonalInfoDto customerPersonalInfoDto = (CustomerPersonalInfoDto) target;

        if (checkAgeMember(customerPersonalInfoDto.getBirthdayCustomer())) {
            errors.rejectValue("dateOfBirth", "birthday.checkAge", "Tuổi phải từ 18 trở lên");
        }
    }

    /* kiem tra tren 18 tuoi */
    public boolean checkAgeMember(String dateOfBirth) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate now = LocalDate.now();

        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])");
        Matcher matcher = pattern.matcher(dateOfBirth);

        boolean isRetry = false;

        if (!matcher.matches()) {
            isRetry = true;
        } else {
            LocalDate birthDay = LocalDate.parse(dateOfBirth, formatter);
            LocalDate after16Years = birthDay.plusYears(18);
            LocalDate before100Years = birthDay.plusYears(100);
            if (after16Years.isAfter(now) || before100Years.isBefore(now)) {
                isRetry = true;
            }
        }
        return isRetry;

    }

}
