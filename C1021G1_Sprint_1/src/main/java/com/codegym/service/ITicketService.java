package com.codegym.service;

import com.codegym.model.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ITicketService {

    Page<Ticket> findAllTicket(Pageable pageable);
//
//    Ticket findTicketById(Long id);
//
//    void deleteTicketById(Long id);

    // Page<Ticket> ticketSalesSearch(String name, Pageable pageable);
}
