package com.codegym.controller;

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
    @GetMapping
    public ResponseEntity<List<Ticket>> listAllTicketList() {
        List<Ticket> ticketList = ticketService.findAll();
        if (ticketList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<>(ticketList, HttpStatus.OK);
    }

    //    SonNh lấy ticket by ticket Id
    @GetMapping(value = "/{id}")
    public ResponseEntity<Ticket> getTicketByID(@PathVariable("id") long id) {
        System.out.println("Fetching Customer with id " + id);
        Ticket ticket = ticketService.findById(id);
        if (ticket == null) {
            System.out.println("Customer with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ticket, HttpStatus.OK);
    }

    //    SonNh update ticket by ticket Id
    @PutMapping(value = "/{id}")
    public ResponseEntity<Ticket> updateTicket(@PathVariable("id") long id, @RequestBody Ticket ticket) {
        System.out.println("Updating Customer " + id);

        Ticket currentTicket = ticketService.findById(id);

        if (currentTicket == null) {
            System.out.println("Customer with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ticket.setId(currentTicket.getId());
        ticketService.save(ticket);
        return new ResponseEntity<>(ticket, HttpStatus.OK);
    }
}
