package com.codegym.model;

import com.codegym.model.Customer;
import com.codegym.model.Employee;
import com.codegym.model.Flight;
import javax.persistence.*;

@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codeTicket;

    private String emailTicket;

    private String phoneTicket;

    private Boolean genderTicket;

    private Boolean statusTicket;
                // Giá vé
    private Double priceTicket;

    private Boolean delFlagTicket;

    private Integer pointTicket;
              // Người mua vé
    private String buyer;

          // Ngày sinh của người mua vé
    private String dayOfBirth;

    @ManyToOne
    @JoinColumn(name = "id_employee", referencedColumnName = "id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "id_customer", referencedColumnName = "id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "id_flight", referencedColumnName = "id")
    private Flight flightTicket;

    public Ticket() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeTicket() {
        return codeTicket;
    }

    public void setCodeTicket(String codeTicket) {
        this.codeTicket = codeTicket;
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

    public Double getPriceTicket() {
        return priceTicket;
    }

    public void setPriceTicket(Double priceTicket) {
        this.priceTicket = priceTicket;
    }

    public Boolean getDelFlagTicket() {
        return delFlagTicket;
    }

    public void setDelFlagTicket(Boolean delFlagTicket) {
        this.delFlagTicket = delFlagTicket;
    }

    public Integer getPointTicket() {
        return pointTicket;
    }

    public void setPointTicket(Integer pointTicket) {
        this.pointTicket = pointTicket;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
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

    public Flight getFlightTicket() {
        return flightTicket;
    }

    public void setFlightTicket(Flight flightTicket) {
        this.flightTicket = flightTicket;
    }

    public String getDayOfBirth() {
        return dayOfBirth;
    }

    public void setDayOfBirth(String dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }
}
