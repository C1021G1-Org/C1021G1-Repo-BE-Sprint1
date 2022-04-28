package com.codegym.controller;

import com.codegym.model.Ticket;
import com.codegym.service.ITicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("ticket/api")
public class TicketController {

    @Autowired
    private ITicketService iTicketService;

    @GetMapping("/list")
    public ResponseEntity<Page<Ticket>> getAllListTicket(@PageableDefault(size = 2) Pageable pageable) {
        Page<Ticket> ticketPage = this.iTicketService.findAllTicket(pageable);
        if (ticketPage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(ticketPage, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Ticket> deleteTicketById(@PathVariable Long id) {
        Ticket tickets = iTicketService.findTicketById(id);
        if (tickets == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        iTicketService.deleteTicketById(id);
        return new ResponseEntity<>(tickets, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Ticket>> findAllTicket(String name, String code, String email, @RequestParam(defaultValue = "0") int page) {
        Page<Ticket> ticketPage = iTicketService.ticketSalesSearch(name,code,email,PageRequest.of(page,10));
        if (ticketPage.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(ticketPage, HttpStatus.OK);
    }

}
