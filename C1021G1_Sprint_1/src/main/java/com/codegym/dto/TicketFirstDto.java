package com.codegym.dto;

import com.codegym.model.Customer;
import com.codegym.model.Employee;
import com.codegym.model.Seat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;


public class TicketFirstDto {

    private Long id;

    @NotBlank(message = "email không được để trống")
    private String emailTicket;

    @NotBlank(message = "số điện thoại không được để trống")
    private String phoneTicket;

    @NotNull(message = "bắt buộc phải chọn giới tính")
    private Boolean genderTicket;

    @NotNull(message = "phai thay đổi giá trị không được null")
    private Boolean statusTicket;

    @NotBlank(message = "tên không được để trống")
    private String buyerTicket;

    @NotBlank(message = "ngày tháng không để trống")
    private String birthdayTicket;


    private Employee employee;

    private Customer customer;



    public TicketFirstDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmailTicket() {
        return emailTicket;
    }

    public void setEmailTicket(String emailTicket) {
        this.emailTicket = emailTicket;
    }

    public String getPhoneTicket() {
        return phoneTicket;
    }

    public void setPhoneTicket(String phoneTicket) {
        this.phoneTicket = phoneTicket;
    }

    public Boolean getGenderTicket() {
        return genderTicket;
    }

    public void setGenderTicket(Boolean genderTicket) {
        this.genderTicket = genderTicket;
    }

    public Boolean getStatusTicket() {
        return statusTicket;
    }

    public void setStatusTicket(Boolean statusTicket) {
        this.statusTicket = statusTicket;
    }


    public String getBuyerTicket() {
        return buyerTicket;
    }

    public void setBuyerTicket(String buyerTicket) {
        this.buyerTicket = buyerTicket;
    }

    public String getBirthdayTicket() {
        return birthdayTicket;
    }

    public void setBirthdayTicket(String birthdayTicket) {
        this.birthdayTicket = birthdayTicket;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


}
