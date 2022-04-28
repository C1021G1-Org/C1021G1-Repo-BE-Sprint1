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

    @Override // danh sách
    public Page<Ticket> findAllTicket(Pageable pageable) {
        return repository.findAllListTicket(pageable);
    }

    @Override // tim id
    public Ticket findTicketById(Long id) {
        return repository.findTicketById(id).orElse(null);
    }

    @Override // xóa theo id
    public void deleteTicketById(Long id) {
        repository.deleteTicketById(id);
    }

    @Override  // tìm kiếm
    public Page<Ticket> ticketSalesSearch(String buyer, String code, String email, Pageable pageable) {
        return repository.findTicketByElementContaining(buyer, code, email,pageable);
    }

}
