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
    ITicketRepository repository;


    @Override
    public List<Ticket> findAllTicketsByCustomerId(Long customerId) {
        return repository.getAllTicketsByCustomerID(customerId);
    }

    @Override
    public List<Ticket> findHistoryTicketsByCustomerId(Long customerId) {
        return repository.getHistoryTicketsByCustomerID(customerId);
    }
//    SonNH lấy list ticket trả về page

    @Override
    public Page<Ticket> findAllTicketsByCustomerIdPage(Long customerId, Pageable pageable) {
        return repository.getAllTicketsByCustomerIDPage(customerId, pageable);
    }

    @Override
    public Page<Ticket> findHistoryTicketsByCustomerIdPage(Long customerId, Pageable pageable) {
        return repository.getHistoryTicketsByCustomerIDPage(customerId, pageable);
    }



    @Override
    public Ticket findTicketByCodeTicket(String codeTicket) {
        return repository.findTicketByCodeTicket(codeTicket);
    }

    @Override
    public void payTicketByCodeTicket(String codeTicket) {
        repository.payTicketByCodeTicket(codeTicket);
        repository.payHistoryTicketByCodeTicket(codeTicket);
    }

    @Override
    public void abortTicketByCodeTicket(String codeTicket) {
        repository.abortTicketByCodeTicket(codeTicket);
        repository.abortHistoryTicketByCodeTicketTicketByCodeTicket(codeTicket);
    }


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
