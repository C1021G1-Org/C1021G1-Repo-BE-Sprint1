package com.codegym.service;

import com.codegym.dto.TicketDto;
import com.codegym.model.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ITicketService {
    List<Ticket> findAllTicketsByCustomerId(Long customerId);

    Ticket findTicketByCodeTicket(String codeTicket);


    void payTicketByCodeTicket(String codeTicket);
}
