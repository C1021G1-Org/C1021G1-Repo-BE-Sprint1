package com.codegym.service.impl;

import com.codegym.model.Ticket;
import com.codegym.repository.ITicketRepository;
import com.codegym.service.ITicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class TicketServiceImpl implements ITicketService {
    @Autowired
    private ITicketRepository repository;

    @Override
    public Page<Ticket> findAllTicket(Pageable pageable) {
        return repository.findAll(pageable);
    }

//    @Override
//    public Ticket findTicketById(Long id) {
//        return repository.findById(id).orElse(null);
//    }
//
//    @Override
//    public void deleteTicketById(Long id) {
//        repository.deleteById(id);
//    }

//    @Override
//    public Page<Ticket> ticketSalesSearch(String name, Pageable pageable) {
//        return repository.s;
//    }
}
