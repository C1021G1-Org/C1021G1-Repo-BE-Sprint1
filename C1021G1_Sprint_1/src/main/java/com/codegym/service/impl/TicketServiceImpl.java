package com.codegym.service.impl;

import com.codegym.dto.EmployeeTicketDto;
import com.codegym.model.Employee;
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

    @Override
    public void updateFirstTicketByIdEmployee(String buyer, String birthDay, String email, Boolean gender, String phone, EmployeeTicketDto employee, Long idTicket) {
        ticketRepository.updateTicketByIdTicketAndIdEmployee(buyer,birthDay,email,gender,phone,employee.getId(),idTicket);
    }

//    @Override
//    public Ticket findById(Long id) {
//        return ticketRepository.findById(id).orElse(null);
//    }


}
