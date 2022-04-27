package com.codegym.controller;

import com.codegym.model.Ticket;
import com.codegym.service.impl.TicketServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TicketController {

    @Autowired
    private TicketServiceImpl ticketService;

    @RequestMapping(value = "/ticket/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Ticket> updateTicket(@PathVariable("id") Long id, @Param("buyer") String buyer , @Param("email_ticket") String email_ticket) {

        Ticket ticket = ticketService.findTicketById(id);

        if (ticket == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {

            ticketService.editTicket(id , buyer, email_ticket);
            return new ResponseEntity<>(ticket, HttpStatus.OK);
        }
    }


}
