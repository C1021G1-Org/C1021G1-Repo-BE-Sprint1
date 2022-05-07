package com.codegym.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nameCustomer;

    private Boolean genderCustomer;

    @Column(columnDefinition = "DATE")
    private String birthdayCustomer;

    private String idCardCustomer;

    private String phoneCustomer;

    private String emailCustomer;

    private String addressCustomer;

    private Boolean delFlagCustomer;

    private Integer pointCustomer;

    @Column(columnDefinition = "LONGTEXT")
    private String imageCustomer;

    @ManyToOne
    @JoinColumn(name = "id_country", referencedColumnName = "id")
    private Countries countries;

    @ManyToOne
    @JoinColumn(name = "id_customer_type", referencedColumnName = "id")
    private CustomerType customerType;

    @JsonBackReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private Set<Ticket> ticketC;
    @JsonBackReference(value = "customer_ticket_history")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private Set<TicketHistory> ticketHistories;

    public Customer() {
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

    public Boolean getDelFlagCustomer() {
        return delFlagCustomer;
    }

    public void setDelFlagCustomer(Boolean delFlagCustomer) {
        this.delFlagCustomer = delFlagCustomer;
    }

    public Integer getPointCustomer() {
        return pointCustomer;
    }

    public void setPointCustomer(Integer pointCustomer) {
        this.pointCustomer = pointCustomer;
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

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }

    public Set<Ticket> getTicketC() {
        return ticketC;
    }

    public void setTicketC(Set<Ticket> ticketC) {
        this.ticketC = ticketC;
    }

    public Set<TicketHistory> getTicketHistories() {
        return ticketHistories;
    }

    public void setTicketHistories(Set<TicketHistory> ticketHistories) {
        this.ticketHistories = ticketHistories;
    }


}
