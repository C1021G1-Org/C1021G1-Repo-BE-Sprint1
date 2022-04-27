package com.codegym.service;

import com.codegym.model.Ticket;

public interface ITicketService {
    void editTicket(Long id, String buyer, String email_ticket);
}
