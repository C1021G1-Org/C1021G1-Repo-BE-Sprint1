package com.codegym.controller;

import com.codegym.dto.TicketDto;
import com.codegym.service.ITicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/ticket")
public class TicketController {

    @Autowired
    private ITicketService ticketService;

    @GetMapping("/not-pagination")
    public ResponseEntity<Page<TicketDto>> getAllTicketNotPagination(@RequestParam(defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page,10);
        Page<TicketDto> ticketDtos = ticketService.getAllTicketDTONotPagination(pageable);
        if (ticketDtos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(ticketDtos, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Iterable<TicketDto>> getAllListTicket(@RequestParam(defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page,10);
        Page<TicketDto> ticketPage = this.ticketService.findAllTicket(pageable);
        if (ticketPage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ticketPage, HttpStatus.OK);
    }

    @GetMapping("/page/{id}")
    public ResponseEntity<?> findCustomerById(@PathVariable Long id) {
        TicketDto ticketDto = ticketService.findTicketById(id);
        if (ticketDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ticketDto, HttpStatus.OK);
    }

    @PatchMapping("/delete/{id}")
    public ResponseEntity<TicketDto> deleteTicketById(@PathVariable Long id) {
        TicketDto tickets = ticketService.findTicketById(id);
        if (tickets == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ticketService.deleteTicketById(id);
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<TicketDto>> findAllTicketSearch(@RequestParam(defaultValue = "", required = false) String keyword,
                                                                   @RequestParam(defaultValue = "", required = false) String option,
                                                                   @RequestParam(defaultValue = "0") int page) {
        Page<TicketDto> ticketPage = null;
        switch (option){
            case "buyer":
                ticketPage = ticketService.ticketByBuyer(keyword,PageRequest.of(page,10));
                break;
            case "toFlight":
                ticketPage = ticketService.ticketToFlight(keyword,PageRequest.of(page,10));
                break;
            case "fromFlight":
                ticketPage = ticketService.ticketFromFlight(keyword,PageRequest.of(page,10));
                break;
            case "code":
                ticketPage = ticketService.ticketCodeTicket(keyword,PageRequest.of(page,10));
                break;
        }
        if (ticketPage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ticketPage, HttpStatus.OK);
    }

}
