package com.codegym.controller;

import com.codegym.dto.ListTicketDto;
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
    public ResponseEntity<Page<com.codegym.model.Ticket>> getAllListTicket(@PageableDefault(size = 2) Pageable pageable) {
        Page<com.codegym.model.Ticket> ticketPage = this.iTicketService.findAllTicket(pageable);
        if (ticketPage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(ticketPage, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<com.codegym.model.Ticket> deleteTicketById(@PathVariable Long id) {
        com.codegym.model.Ticket tickets = iTicketService.findTicketById(id);
        if (tickets == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        iTicketService.deleteTicketById(id);
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<ListTicketDto>> findAllTicketSearch(@RequestParam(defaultValue = "", required = false)String buyer, @RequestParam(defaultValue = "", required = false) String code, @RequestParam(defaultValue = "", required = false) String flight, @RequestParam(defaultValue = "0") int page) {
        Page<ListTicketDto> ticketPage = iTicketService.ticketSalesSearch(buyer, code, flight, PageRequest.of(page, 2));
        if (ticketPage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ticketPage, HttpStatus.OK);
    }

}
