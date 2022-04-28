package com.codegym.service;

import com.codegym.dto.TicketDto;
import com.codegym.model.Ticket;

import java.util.List;

public interface ITicketService {
    List<Ticket> findAllTicketsByCustomerId(Long customerId);

    Ticket findTicketByCodeTicket(String codeTicket);


    void payTicketByCodeTicket(String code, Ticket ticket);
}
