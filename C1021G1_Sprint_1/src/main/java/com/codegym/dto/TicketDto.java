package com.codegym.dto;


import com.codegym.model.Customer;
import com.codegym.model.Employee;
import com.codegym.model.Seat;

import javax.persistence.*;


public interface TicketDto {

    Long getIdTicket();

    String getBirthdayTicket();

    String getBuyerTicket();

    String getCodeTicket();

    Boolean getDelFlagTicket();

    String getEmailTicket();

    Boolean getGenderTicket();

    String getPhoneTicket();

    String getPointTicket();

    Double getPriceTicket();

    Boolean getStatusTicket();

    Long getIdCustomer();

    Long getIdEmployee();

    Long getIdSeat();

    Long getIdFlight();

    String getNameCustomer();

    String getNameEmployee();

    String getCodeSeat();

    String getToFlight();

    String getFromFlight();

    String getDateStart();

    String getNameSeatType();

}
