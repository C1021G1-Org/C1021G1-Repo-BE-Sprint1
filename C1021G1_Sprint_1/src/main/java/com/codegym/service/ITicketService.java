package com.codegym.service;

import com.codegym.model.Ticket;

import java.util.List;

public interface ITicketService {
    List<Ticket> findAll();

    Ticket findById(Long id);

    void save(Ticket customer);
}
