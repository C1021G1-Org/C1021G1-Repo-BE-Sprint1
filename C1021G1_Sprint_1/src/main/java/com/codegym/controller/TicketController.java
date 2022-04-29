package com.codegym.controller;

import com.codegym.dto.EditTicketDto;
import com.codegym.model.Ticket;
import com.codegym.service.impl.TicketServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin("*")
public class TicketController {

    @Autowired
    private TicketServiceImpl ticketService;

    @RequestMapping(value = "/ticket/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findTicket(@PathVariable Long id) {
        Ticket ticket = ticketService.findTicketById(id);
        if(ticket == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(ticket, HttpStatus.OK);
        }
    }

        @RequestMapping(value = "/editTicket/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<?> updateTicket( @Valid @RequestBody EditTicketDto ticket, BindingResult bindingResult) {
            if (bindingResult.hasErrors()) {
                return new ResponseEntity<>(bindingResult.getAllErrors().get(0).getDefaultMessage(), HttpStatus.NOT_FOUND);
            }

            ticketService.editTicket(ticket);
            return new ResponseEntity<Void>(HttpStatus.OK);
    }


}
