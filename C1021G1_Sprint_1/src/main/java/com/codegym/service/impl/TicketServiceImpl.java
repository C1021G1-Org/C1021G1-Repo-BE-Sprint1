package com.codegym.service.impl;

import com.codegym.dto.EmployeeTicketDto;
import com.codegym.model.Employee;
import com.codegym.model.Flight;
import com.codegym.model.SeatType;
import com.codegym.model.Ticket;
import com.codegym.repository.IFlightRepository;
import com.codegym.repository.ISeatTypeRepository;
import com.codegym.repository.ITicketRepository;
import com.codegym.service.ITicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements ITicketService {
    @Autowired
    private ITicketRepository ticketRepository;

    @Autowired
    private IFlightRepository flightRepository;

    @Autowired
    private ISeatTypeRepository seatTypeRepository;

    @Override
    public List<Ticket> getListNumberTicket(Long idFlight, String typeSeat) {
        return ticketRepository.getListNumberTicket(idFlight, typeSeat);
    }

    @Override
    public Ticket getTicketByFlightIdAndTypeSeatAndTicketId(Long idFlight, String typeSeat, Long idTicket) {
        return ticketRepository.getTicketByFlightIdAndTypeSeatAndTicketId(idFlight, typeSeat, idTicket);
    }

    @Override
    public void updateFirstTicket(String buyer, String birthDay, String email, Boolean gender, String phone, String dateTicket, Double price, String idCard, Long employee, Long customer, Long idTicket) {
        ticketRepository.updateTicketByIdTicketAndIdEmployee(buyer, birthDay, email, gender, phone, dateTicket, price, idCard, employee, customer, idTicket);
    }


//    @Override
//    public void updateFirstTicket(String buyer, String birthDay, String email, Boolean gender, String phone, Double price, String idCard, Long employee, Long customer, Long idTicket) {
//        ticketRepository.updateTicketByIdTicketAndIdEmployee(buyer, birthDay, email, gender, phone, price, idCard, employee, customer, idTicket);
//    }

//    @Override
//    public void updateFirstTicket(String buyer, String birthDay, String email, Boolean gender, String phone, Double price, String idCard, Long employee, Long idTicket) {
//        ticketRepository.updateTicketByIdTicketAndIdEmployee(buyer, birthDay, email, gender, phone,price,idCard,employee,idTicket);
//    }

//    @Override
//    public void updateFirstTicket(String buyer, String birthDay, String email, Boolean gender, String phone, Double price, Long employee , Long idTicket) {
//        ticketRepository.updateTicketByIdTicketAndIdEmployee(buyer, birthDay, email, gender, phone,price,employee, idTicket);
//    }

//    @Override
//    public void updateFirstTicketByIdEmployee(String buyer, String birthDay, String email, Boolean gender, String phone, EmployeeTicketDto employee, Long idTicket) {
//        ticketRepository.updateTicketByIdTicketAndIdEmployee(buyer, birthDay, email, gender, phone, employee.getId(), idTicket);
//    }

    @Override
    public Flight findFlightById(Long id) {
        return flightRepository.findById(id).orElse(null);
    }

    @Override
    public List<SeatType> getAllSeatTypes() {
        return seatTypeRepository.findAll();
    }

    @Override
    public void addTicketHistory(String birthdayTicket, String buyerTicket, String codeTicket, Boolean delFlagTicket, String emailTicket, Boolean genderTicket, String phoneTicket, Integer pointTicket, Double priceTicket, Boolean statusTicket, Long idCustomer, Long idEmployee, Long idSeat, String dateTicket, String idCard) {
        ticketRepository.addTicketHistory(birthdayTicket, buyerTicket, codeTicket, delFlagTicket, emailTicket, genderTicket, phoneTicket, pointTicket, priceTicket, statusTicket, idCustomer, idEmployee, idSeat, dateTicket, idCard);
    }

    @Override
    public Ticket getTicketAddHistory(Long idTicket) {
        return ticketRepository.getTicketAddHistory(idTicket);
    }


}
