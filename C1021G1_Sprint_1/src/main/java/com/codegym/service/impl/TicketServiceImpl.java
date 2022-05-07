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
    private ITicketRepository repository;

    @Override // danh sách
    public Page<TicketDto> findAllTicket(Pageable pageable) {
        return repository.findAllListTicket(pageable);
    }

    @Override // tim id
    public TicketDto findTicketById(Long id) {
        return repository.findTicketById(id).orElse(null);
    }

    @Override // xóa theo id
    public void deleteTicketById(Long id) {
        repository.deleteTicketById(id);
    }

    @Override  // tìm kiếm
    public Page<TicketDto> ticketByBuyer(String keyword, Pageable pageable) {
        return repository.ticketByBuyer(keyword, pageable);
    }

    @Override  // tìm kiếm
    public Page<TicketDto> ticketFromFlight(String keyword, Pageable pageable) {
        return repository.ticketFromFlight(keyword, pageable);
    }

    @Override  // tìm kiếm
    public Page<TicketDto> ticketToFlight(String keyword, Pageable pageable) {
        return repository.ticketToFlight(keyword, pageable);
    }

    @Override  // tìm kiếm
    public Page<TicketDto> ticketCodeTicket(String keyword, Pageable pageable) {
        return repository.ticketCodeTicket(keyword, pageable);
    }

    @Override
    public Page<TicketDto> getAllTicketDTONotPagination(Pageable pageable) {
        return repository.getAllTicketDTONotPagination(pageable);
    }

}
