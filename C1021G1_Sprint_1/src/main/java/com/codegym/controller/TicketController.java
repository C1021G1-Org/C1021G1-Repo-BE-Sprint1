package com.codegym.controller;

import com.codegym.model.Ticket;
import com.codegym.repository.ITicketRepository;
import com.codegym.service.impl.TicketServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TicketController {

    @Autowired
    private TicketServiceImpl ticketService;

    @GetMapping("/listTicketType")
    public ResponseEntity<List<Ticket>> getListTicketByIdFlight(@RequestParam(defaultValue = "")Long id, @RequestParam(defaultValue = "") String typeSeat)
    {

        List<Ticket> ticketList = ticketService.getListNumberTicket(id, typeSeat);

        if (ticketList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(ticketList, HttpStatus.OK);
        }
    }



}
