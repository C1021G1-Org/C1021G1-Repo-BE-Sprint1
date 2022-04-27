package com.codegym.service.impl;

import com.codegym.repository.ITicketRepository;
import com.codegym.service.ITicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketServiceImpl implements ITicketService {
    @Autowired
    ITicketRepository ticketRepository;


    @Override
    public void editTicket(Long id, String buyer, String email_ticket) {
        ticketRepository.editTicket(id, buyer, email_ticket);
    }
}
