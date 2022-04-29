package com.codegym.controller;

import com.codegym.dto.TicketDto;
import com.codegym.model.Ticket;
import com.codegym.service.ITicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ticket")
public class TicketController {
    @Autowired
    ITicketService ticketService;

    //    SonNh lấy danh sách ticket by customer Id
    @GetMapping("/list/{id}")
    public ResponseEntity<List<Ticket>> listAllTicketListByCustomerId(@PathVariable Long id) {
        List<Ticket> ticketList = ticketService.findAllTicketsByCustomerId(id);
        if (ticketList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<>(ticketList, HttpStatus.OK);
    }

    //    SonNh lấy ticket by CodeTicket
    @GetMapping(value = "/{code_ticket}")
    public ResponseEntity<Ticket> getTicketByID(@PathVariable("code_ticket") String codeTicket) {
        System.out.println("Fetching Ticket with id " + codeTicket);
        Ticket ticket = ticketService.findTicketByCodeTicket(codeTicket);
        if (ticket == null) {
            System.out.println("Ticket with id " + codeTicket + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ticket, HttpStatus.OK);
    }

    //    SonNh update ticket by ticket Id
    @PatchMapping(value = "/pay/{code}")
    public ResponseEntity<Ticket> updateTicket(@PathVariable("code") String codeTicket) {
        System.out.println("Fetching Ticket with id " + codeTicket);
        Ticket ticket = ticketService.findTicketByCodeTicket(codeTicket);
        if (ticket == null) {
            System.out.println("Ticket with id " + codeTicket + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ticketService.payTicketByCodeTicket(codeTicket);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PatchMapping(value = "/abort/{code}")
    public ResponseEntity<Ticket> abortTicket(@PathVariable("code") String codeTicket) {
        System.out.println("Fetching Ticket with id " + codeTicket);
        Ticket ticket = ticketService.findTicketByCodeTicket(codeTicket);
        if (ticket == null) {
            System.out.println("Ticket with id " + codeTicket + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ticketService.abortTicketByCodeTicket(codeTicket);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
