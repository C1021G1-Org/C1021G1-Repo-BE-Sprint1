package com.codegym.service;

import com.codegym.dto.ListTicketDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ITicketService {

    Page<com.codegym.model.Ticket> findAllTicket(Pageable pageable);

    com.codegym.model.Ticket findTicketById(Long id);

    void deleteTicketById(Long id);

     Page<ListTicketDto> ticketSalesSearch(String buyer, String code, String flight, Pageable pageable);
}
