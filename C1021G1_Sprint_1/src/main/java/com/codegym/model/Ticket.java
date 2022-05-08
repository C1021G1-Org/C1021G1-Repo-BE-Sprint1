package com.codegym.model;
import com.codegym.model.Customer;
import com.codegym.model.Employee;
import com.codegym.model.Flight;
import com.fasterxml.jackson.annotation.JsonBackReference;

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

    private String idCard;


    //ngay dat ve
    @Column(columnDefinition = "DATE")
    private String dateTicket;

    // Người mua vé
    private String buyerTicket;

    @Column(columnDefinition = "DATE")
    // Ngày sinh của người mua vé
    private String birthdayTicket;


    @ManyToOne
    @JoinColumn(name = "id_employee", referencedColumnName = "id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "id_customer", referencedColumnName = "id")
    private Customer customer;


    @OneToOne
    @JoinColumn(name = "id_seat", referencedColumnName = "id")
    private Seat seat;


    public Ticket() {
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

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getDateTicket() {
        return dateTicket;
    }

    public void setDateTicket(String dateTicket) {
        this.dateTicket = dateTicket;
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
