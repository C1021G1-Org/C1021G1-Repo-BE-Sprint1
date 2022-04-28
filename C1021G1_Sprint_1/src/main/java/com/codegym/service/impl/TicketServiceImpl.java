package com.codegym.service.impl;

import com.codegym.model.Ticket;
import com.codegym.repository.ITicketRepository;
import com.codegym.service.ITicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements ITicketService {
@Autowired
private ITicketRepository ticketRepository;


    @Override
    public List<Ticket> getListNumberTicket(Long idFlight, String typeSeat) {
        return ticketRepository.getListNumberTicket(idFlight,typeSeat);
    }

    @Override
    public Ticket getTicketByFlightIdAndTypeSeatAndTicketId(Long idFlight, String typeSeat, Long idTicket) {
        return ticketRepository.getTicketByFlightIdAndTypeSeatAndTicketId(idFlight,typeSeat,idTicket);
    }


}
