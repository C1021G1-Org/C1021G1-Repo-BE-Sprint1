package com.codegym.service.impl;

import com.codegym.dto.EditTicketDto;
import com.codegym.model.Ticket;
import com.codegym.repository.ITicketRepository;
import com.codegym.service.ITicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class TicketServiceImpl implements ITicketService {

    @Autowired
    ITicketRepository ticketRepository;


    @Override
    public void editTicket( EditTicketDto editTicketDto) {
        ticketRepository.editTicket( editTicketDto.getBuyer(), editTicketDto.getEmailTicket(), editTicketDto.getId());
    }

    @Override
    public Ticket findTicketById(Long id) {
        return ticketRepository.findTicketById(id);
    }
}
