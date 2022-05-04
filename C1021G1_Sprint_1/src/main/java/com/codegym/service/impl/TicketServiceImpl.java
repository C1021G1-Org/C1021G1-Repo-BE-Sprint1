package com.codegym.service.impl;

import com.codegym.dto.TicketDto;
import com.codegym.model.Ticket;
import com.codegym.repository.ITicketRepository;
import com.codegym.service.ITicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements ITicketService {
    @Autowired
    ITicketRepository ticketRepository;


    @Override
    public List<Ticket> findAllTicketsByCustomerId(Long customerId) {
        return ticketRepository.getAllTicketsByCustomerID(customerId);
    }

    @Override
    public List<Ticket> findHistoryTicketsByCustomerId(Long customerId) {
        return ticketRepository.getHistoryTicketsByCustomerID(customerId);
    }

    @Override
    public Ticket findTicketByCodeTicket(String codeTicket) {
        return ticketRepository.findTicketByCodeTicket(codeTicket);
    }

    @Override
    public void payTicketByCodeTicket(String codeTicket) {
        ticketRepository.payTicketByCodeTicket(codeTicket);
        ticketRepository.payHistoryTicketByCodeTicket(codeTicket);
    }
    @Override
    public void abortTicketByCodeTicket(String codeTicket) {
        ticketRepository.abortTicketByCodeTicket(codeTicket);
        ticketRepository.abortHistoryTicketByCodeTicketTicketByCodeTicket(codeTicket);
    }


}
