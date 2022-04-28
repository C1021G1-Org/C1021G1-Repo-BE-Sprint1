package com.codegym.dto;

import com.codegym.model.Countries;
import com.codegym.model.CustomerType;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public interface ViewCusDto {
    Long getId() ;

    String nameCustomer();

    Boolean getGenderCustomer();

    String getBirthdayCustomer();

    String getIdCardCustomer();

    String getPhoneCustomer();

    String getEmailCustomer();

    String getAddressCustomer();

    Integer getPointCustomer();

    String getImageCustomer();

    Long GetCountriesID();

    Long getCustomerTypeID();


}
