package com.codegym.service;

import com.codegym.dto.EditTicketDto;
import com.codegym.dto.TicketDto;
import com.codegym.model.Ticket;

import java.util.Optional;

public interface ITicketService {
    void editTicket( EditTicketDto editTicketDto);
    Ticket findTicketById(Long id);
}
