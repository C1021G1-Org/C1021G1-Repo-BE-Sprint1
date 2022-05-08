package com.codegym.service;


import com.codegym.dto.EmployeeTicketDto;
import com.codegym.model.*;
import org.springframework.data.repository.query.Param;

import com.codegym.dto.TicketDto;
import com.codegym.model.Ticket;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


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

    List<Ticket> findAllTicketsByCustomerId(Long customerId);

    List<Ticket> findHistoryTicketsByCustomerId(Long customerId);

    Page<Ticket> findAllTicketsByCustomerIdPage(Long customerId,Pageable pageable);

    Page<Ticket> findHistoryTicketsByCustomerIdPage(Long customerId,Pageable pageable);



    Ticket findTicketByCodeTicket(String codeTicket);


    void payTicketByCodeTicket(String codeTicket);
    void abortTicketByCodeTicket(String codeTicket);

    Page<TicketDto> findAllTicket(Pageable pageable);

//    Page<TicketDto> findAllTicketDTO(Pageable pageable);
//
//    List<Ticket> findAllTicketDto(int index);

    TicketDto findTicketById(Long id);

    void deleteTicketById(Long id);

    Page<TicketDto> ticketByBuyer(String keyword, Pageable pageable);

    Page<TicketDto> ticketFromFlight(String keyword, Pageable pageable);

    Page<TicketDto> ticketToFlight(String keyword, Pageable pageable);

    Page<TicketDto> ticketCodeTicket(String keyword, Pageable pageable);


    Page<TicketDto> getAllTicketDTONotPagination(Pageable pageable);

}
