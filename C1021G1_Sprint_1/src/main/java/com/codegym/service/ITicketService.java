package com.codegym.service;

import com.codegym.dto.EmployeeTicketDto;
import com.codegym.model.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ITicketService {


    List<Ticket> getListNumberTicket(@Param("idFlight") Long idFlight, @Param("typeSeat") String typeSeat);

    Ticket getTicketByFlightIdAndTypeSeatAndTicketId(@Param("idFlight") Long idFlight, @Param("typeSeat") String typeSeat, @Param("idTicket") Long idTicket);

    void updateFirstTicket(
            @Param("buyer") String buyer,
            @Param("birthDay") String birthDay,
            @Param("email") String email,
            @Param("gender") Boolean gender,
            @Param("phone") String phone,
            @Param("dateTicket") String dateTicket,
            @Param("price") Double price,
            @Param("idCard") String idCard,
            @Param("employee") Long employee,
            @Param("customer") Long customer,
            @Param("idTicket") Long idTicket

    );

//    Ticket findById(Long id);

    Flight findFlightById(Long id);

    List<SeatType> getAllSeatTypes();


    void addTicketHistory(
            @Param("birthdayTicket") String birthdayTicket,
            @Param("buyerTicket") String buyerTicket,
            @Param("codeTicket") String codeTicket,
            @Param("delFlagTicket") Boolean delFlagTicket,
            @Param("emailTicket") String emailTicket,
            @Param("genderTicket") Boolean genderTicket,
            @Param("phoneTicket") String phoneTicket,
            @Param("pointTicket") Integer pointTicket,
            @Param("priceTicket") Double priceTicket,
            @Param("statusTicket") Boolean statusTicket,
            @Param("idCustomer") Long idCustomer,
            @Param("idEmployee") Long idEmployee,
            @Param("idSeat") Long idSeat,
            @Param("dateTicket") String dateTicket,
            @Param("idCard") String idCard);

    Ticket getTicketAddHistory(@Param("idTicket")Long idTicket);
}
