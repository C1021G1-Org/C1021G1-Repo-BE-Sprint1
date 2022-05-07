package com.codegym.service;

import com.codegym.dto.TicketDto;
import com.codegym.model.Ticket;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ITicketService {

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
