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

    @Autowired
    private IFlightRepository flightRepository;

    @Autowired
    private ISeatTypeRepository seatTypeRepository;

    @Override
    public List<Ticket> getListNumberTicket(Long idFlight, String typeSeat) {
        return repository.getListNumberTicket(idFlight, typeSeat);
    }

    @Override
    public Ticket getTicketByFlightIdAndTypeSeatAndTicketId(Long idFlight, String typeSeat, Long idTicket) {
        return repository.getTicketByFlightIdAndTypeSeatAndTicketId(idFlight, typeSeat, idTicket);
    }

    @Override
    public void updateFirstTicket(String buyer, String birthDay, String email, Boolean gender, String phone, String dateTicket, Double price, String idCard, Long employee, Long customer, Long idTicket) {
        repository.updateTicketByIdTicketAndIdEmployee(buyer, birthDay, email, gender, phone, dateTicket, price, idCard, employee, customer, idTicket);
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
        repository.addTicketHistory(birthdayTicket, buyerTicket, codeTicket, delFlagTicket, emailTicket, genderTicket, phoneTicket, pointTicket, priceTicket, statusTicket, idCustomer, idEmployee, idSeat, dateTicket, idCard);
    }

    @Override
    public Ticket getTicketAddHistory(Long idTicket) {
        return repository.getTicketAddHistory(idTicket);
    }

    @Override
    public Long getIdCustomerEmailRole(String emailCustomer) {
        return repository.getIdCustomerEmailRole(emailCustomer);
    }

    @Override
    public Long getIdEmployeeByEmailRole(String emailEmployee) {
        return repository.getIdEmployeeByEmailRole(emailEmployee);
    }


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
