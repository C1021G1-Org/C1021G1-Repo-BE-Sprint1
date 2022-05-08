package com.codegym.dto;

import com.codegym.model.EmployeeType;
import com.codegym.model.Ticket;
import com.codegym.model.TicketHistory;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Set;

public class EmployeeTicketDto {

    private Long id;

    private String nameEmployee;

    private String codeEmployee;

    private Boolean genderEmployee;


    private String birthdayEmployee;

    private String phoneEmployee;

    private String emailEmployee;

    private String addressEmployee;

    private Boolean delFlagEmployee;


    private EmployeeType employeeType;


    public EmployeeTicketDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameEmployee() {
        return nameEmployee;
    }

    public void setNameEmployee(String nameEmployee) {
        this.nameEmployee = nameEmployee;
    }

    public String getCodeEmployee() {
        return codeEmployee;
    }

    public void setCodeEmployee(String codeEmployee) {
        this.codeEmployee = codeEmployee;
    }

    public Boolean getGenderEmployee() {
        return genderEmployee;
    }

    public void setGenderEmployee(Boolean genderEmployee) {
        this.genderEmployee = genderEmployee;
    }

    public String getBirthdayEmployee() {
        return birthdayEmployee;
    }

    public void setBirthdayEmployee(String birthdayEmployee) {
        this.birthdayEmployee = birthdayEmployee;
    }

    public String getPhoneEmployee() {
        return phoneEmployee;
    }

    public void setPhoneEmployee(String phoneEmployee) {
        this.phoneEmployee = phoneEmployee;
    }

    public String getEmailEmployee() {
        return emailEmployee;
    }

    public void setEmailEmployee(String emailEmployee) {
        this.emailEmployee = emailEmployee;
    }

    public String getAddressEmployee() {
        return addressEmployee;
    }

    public void setAddressEmployee(String addressEmployee) {
        this.addressEmployee = addressEmployee;
    }

    public Boolean getDelFlagEmployee() {
        return delFlagEmployee;
    }

    public void setDelFlagEmployee(Boolean delFlagEmployee) {
        this.delFlagEmployee = delFlagEmployee;
    }

    public EmployeeType getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(EmployeeType employeeType) {
        this.employeeType = employeeType;
    }
}
