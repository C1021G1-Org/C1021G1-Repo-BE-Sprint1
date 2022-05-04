package com.codegym.controller;

import com.codegym.dto.TicketDto;
import com.codegym.model.Ticket;
import com.codegym.service.ITicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/ticket")
public class TicketController {

    @Autowired
    private ITicketService iTicketService;

//    @GetMapping("/list")
//    public ResponseEntity<List<Ticket>> getAllListTicket(@RequestParam int index) {
//        List<Ticket> tickets = this.iTicketService.findAllTicketDto(index);
//        if (tickets.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>(tickets, HttpStatus.OK);
//    }

    @GetMapping("/not-pagination")
    public ResponseEntity<List<TicketDto>> getAllTicketNotPagination() {
        List<TicketDto> vaccines = iTicketService.getAllTicketDTONotPagination();
        if (vaccines.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(vaccines, HttpStatus.OK);
    }


    //    @GetMapping("/list")
//    public ResponseEntity<Page<Ticket>> getAllListTicket(@PageableDefault(size = 2) Pageable pageable) {
//        Page<Ticket> ticketPage = this.iTicketService.findAllTicket(pageable);
//        if (ticketPage.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>(ticketPage, HttpStatus.OK);
//    }

    @GetMapping("/page")
    public ResponseEntity<Iterable<TicketDto>> getAllListTicket(@RequestParam(defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page,10);
        Page<TicketDto> ticketPage = this.iTicketService.findAllTicket(pageable);
        if (ticketPage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ticketPage, HttpStatus.OK);
    }

//    @GetMapping("/list")
//    public ResponseEntity<Page<ListTicketDto>> getAllListTicket(@RequestParam("page") int page) {
//        PageRequest pageable = PageRequest.of(page - 1, 15);
//        Page<ListTicketDto> ticketPage = this.iTicketService.findAllTicketDTO(pageable);
//        if (ticketPage.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>(ticketPage, HttpStatus.OK);
//    }
//
    @PatchMapping("/delete/{id}")
    public ResponseEntity<TicketDto> deleteTicketById(@PathVariable Long id) {
        TicketDto tickets = iTicketService.findTicketById(id);
        if (tickets == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        iTicketService.deleteTicketById(id);
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<TicketDto>> findAllTicketSearch(@RequestParam(defaultValue = "", required = false) String keyword,
                                                                   @RequestParam(defaultValue = "", required = false) String option,
                                                                   @RequestParam(defaultValue = "0") int page) {
        Page<TicketDto> ticketPage = null;
        switch (option){
            case "buyer":
                ticketPage = iTicketService.ticketByBuyer(keyword,PageRequest.of(page,10));
                break;
            case "toFlight":
                ticketPage = iTicketService.ticketToFlight(keyword,PageRequest.of(page,10));
                break;
            case "fromFlight":
                ticketPage = iTicketService.ticketFromFlight(keyword,PageRequest.of(page,10));
                break;
            case "code":
                ticketPage = iTicketService.ticketCodeTicket(keyword,PageRequest.of(page,10));
                break;
        }
        if (ticketPage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ticketPage, HttpStatus.OK);
    }

}
