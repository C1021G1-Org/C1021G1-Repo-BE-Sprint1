package com.codegym.controller;

import com.codegym.dto.TicketFirstDto;
import com.codegym.model.Ticket;
import com.codegym.repository.ITicketRepository;
import com.codegym.service.impl.TicketServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class TicketController {

    @Autowired
    private TicketServiceImpl ticketService;

    @GetMapping("/listTicketType")
    public ResponseEntity<List<Ticket>> getListTicketByIdFlight(@RequestParam(defaultValue = "") Long id, @RequestParam(defaultValue = "") String typeSeat) {

        List<Ticket> ticketList = ticketService.getListNumberTicket(id, typeSeat);

        if (ticketList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(ticketList, HttpStatus.OK);
        }
    }

    @GetMapping("/findTicket")
    public ResponseEntity<Ticket> getFirstTicketById(@RequestParam(defaultValue = "") Long idFlight,
                                                     @RequestParam(defaultValue = "") String typeSeat,
                                                     @RequestParam(defaultValue = "") Long idTicket) {

        Ticket ticket = ticketService.getTicketByFlightIdAndTypeSeatAndTicketId(idFlight, typeSeat, idTicket);

        if (ticket == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(ticket, HttpStatus.OK);
        }
    }

    @PatchMapping("/firstUpdate")
    public ResponseEntity<?> updateFirstTicket(@Valid @RequestBody TicketFirstDto ticketFirstDto, BindingResult bindingResult,
                                               @RequestParam(defaultValue = "") Long idFlight,
                                               @RequestParam(defaultValue = "") String typeSeat) {
        if (bindingResult.hasFieldErrors()) {
            Map<String, String> errorMap = new HashMap<>();
            Map<String, Object> response = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> {
                errorMap.put(error.getField(), error.getDefaultMessage());
            });
            response.put("error", errorMap);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        } else {
            if (ticketService.getTicketByFlightIdAndTypeSeatAndTicketId(idFlight, typeSeat, ticketFirstDto.getId()) == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                ticketService.updateFirstTicketByIdEmployee(ticketFirstDto.getBuyerTicket(),
                        ticketFirstDto.getBirthdayTicket(), ticketFirstDto.getEmailTicket(), ticketFirstDto.getGenderTicket(),
                        ticketFirstDto.getPhoneTicket(),ticketFirstDto.getEmployeeTicketDto(), ticketFirstDto.getId());

            }
            return new ResponseEntity<Ticket>(ticketService.getTicketByFlightIdAndTypeSeatAndTicketId(idFlight, typeSeat, ticketFirstDto.getId()),
                    HttpStatus.OK);
        }

    }



}
