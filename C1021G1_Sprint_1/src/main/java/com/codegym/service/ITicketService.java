package com.codegym.service;

import com.codegym.dto.EditTicketDto;
import com.codegym.model.Ticket;


public interface ITicketService {
    void editTicket( EditTicketDto editTicketDto);
    Ticket findTicketById(Long id);
}
