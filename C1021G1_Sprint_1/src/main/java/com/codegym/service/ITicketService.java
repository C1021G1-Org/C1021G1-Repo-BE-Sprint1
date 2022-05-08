package com.codegym.service;

import com.codegym.dto.EmployeeTicketDto;
import com.codegym.model.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ITicketService {


    List<Ticket> getListNumberTicket(Long idFlight, String typeSeat);

    Ticket getTicketByFlightIdAndTypeSeatAndTicketId(Long idFlight, String typeSeat, Long idTicket);

    void updateFirstTicket(
            String buyer,
            String birthDay,
            String email,
            Boolean gender,
            String phone,
            String dateTicket,
            Double price,
            String idCard,
            Long employee,
            Long customer,
            Long idTicket

    );


//    Ticket findById(Long id);

    Flight findFlightById(Long id);

    List<SeatType> getAllSeatTypes();


    void addTicketHistory(
            String birthdayTicket,
            String buyerTicket,
            String codeTicket,
            Boolean delFlagTicket,
            String emailTicket,
            Boolean genderTicket,
            String phoneTicket,
            Integer pointTicket,
            Double priceTicket,
            Boolean statusTicket,
            Long idCustomer,
            Long idEmployee,
            Long idSeat,
            String dateTicket,
            String idCard);

    Ticket getTicketAddHistory(Long idTicket);

    Long getIdCustomerEmailRole(String emailCustomer);

    Long getIdEmployeeByEmailRole(String emailEmployee);
}
