package com.codegym.dto;

import com.codegym.model.EmployeeType;
import com.codegym.model.Ticket;

import java.util.Set;

public class EmployeeDto {
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

    public EmployeeDto() {
    }

    public EmployeeDto(Long id, String nameEmployee, String codeEmployee, Boolean genderEmployee,
                       String birthdayEmployee, String phoneEmployee, String emailEmployee, String addressEmployee,
                       Boolean delFlagEmployee, EmployeeType employeeType, Set<Ticket> ticketEmployee) {
        this.id = id;
        this.nameEmployee = nameEmployee;
        this.codeEmployee = codeEmployee;
        this.genderEmployee = genderEmployee;
        this.birthdayEmployee = birthdayEmployee;
        this.phoneEmployee = phoneEmployee;
        this.emailEmployee = emailEmployee;
        this.addressEmployee = addressEmployee;
        this.delFlagEmployee = delFlagEmployee;
        this.employeeType = employeeType;
        this.ticketEmployee = ticketEmployee;
    }

    private Set<Ticket> ticketEmployee;

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

    public Set<Ticket> getTicketEmployee() {
        return ticketEmployee;
    }

    public void setTicketEmployee(Set<Ticket> ticketEmployee) {
        this.ticketEmployee = ticketEmployee;
    }
}
