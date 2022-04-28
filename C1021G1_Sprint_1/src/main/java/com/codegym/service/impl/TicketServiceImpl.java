package com.codegym.service.impl;

import com.codegym.dto.TicketDto;
import com.codegym.model.Ticket;
import com.codegym.repository.ITicketRepository;
import com.codegym.service.ITicketService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Ticket findTicketByCodeTicket(String codeTicket) {
        return ticketRepository.findTicketByCodeTicket(codeTicket);
    }


    @Override
    public void payTicketByCodeTicket(String code, Ticket ticket) {
        String birthday2 = ticket.getBirthdayTicket();
        String buyer3 = ticket.getBuyerTicket();
        String codeticket4 = ticket.getCodeTicket();
        Boolean delFlas5 = ticket.getDelFlagTicket();
        String email6 = ticket.getEmailTicket();
        Boolean gender7 = ticket.getGenderTicket();
        String phone8 = ticket.getPhoneTicket();
        int point9 = ticket.getPointTicket();
        double price10 = ticket.getPriceTicket();
        Boolean status11 = ticket.getStatusTicket();
        Long customerId12 = ticket.getCustomer().getId();
        Long employeeId13 = ticket.getEmployee().getId();
        String codeticket15 = code;
       ticketRepository.payTicketByCodeTicket(
               birthday2,
               buyer3,
               codeticket4,
               delFlas5,
               email6,
               gender7,
               phone8,
               point9,
               price10,
               status11,
               customerId12,
               employeeId13,
               codeticket15
       );
    }
}
