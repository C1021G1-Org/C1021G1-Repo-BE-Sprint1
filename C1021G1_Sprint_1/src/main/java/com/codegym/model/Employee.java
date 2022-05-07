package com.codegym.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nameEmployee;

    private String codeEmployee;

    private Boolean genderEmployee;

    @Column(columnDefinition = "DATE")
    private String birthdayEmployee;

    private String phoneEmployee;

    private String emailEmployee;

    private String addressEmployee;

    private Boolean delFlagEmployee;

    @ManyToOne
    @JoinColumn(name = "id_employee_type", referencedColumnName = "id")
    private EmployeeType employeeType;

    @JsonBackReference(value = "employee_ticket")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
    private Set<Ticket> ticketEmployee;

    public Employee() {
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

    public Set<Ticket> getTicketEmployee() {
        return ticketEmployee;
    }

    public void setTicketEmployee(Set<Ticket> ticketEmployee) {
        this.ticketEmployee = ticketEmployee;
    }
}
