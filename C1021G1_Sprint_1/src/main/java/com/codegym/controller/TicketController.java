package com.codegym.controller;

import com.codegym.common.ticket.MyConstants;
import com.codegym.dto.TicketFirstDto;
import com.codegym.model.Flight;
import com.codegym.model.SeatType;
import com.codegym.model.Ticket;
import com.codegym.service.impl.TicketServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/api/ticket")
public class TicketController {

    @Autowired
    private TicketServiceImpl ticketService;
    @Autowired
    public JavaMailSender mailSender;

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
                Boolean gender = Boolean.parseBoolean(ticketFirstDto.getGenderTicket());
//                ticketService.updateFirstTicketByIdEmployee(ticketFirstDto.getBuyerTicket(),
//                        ticketFirstDto.getBirthdayTicket(), ticketFirstDto.getEmailTicket(), ticketFirstDto.getGenderTicket(),
//                        ticketFirstDto.getPhoneTicket(), ticketFirstDto.getEmployeeTicketDto(), ticketFirstDto.getId());
                ticketService.updateFirstTicket(ticketFirstDto.getBuyerTicket(),
                        ticketFirstDto.getBirthdayTicket(), ticketFirstDto.getEmailTicket(), gender,
                        ticketFirstDto.getPhoneTicket(), ticketFirstDto.getPriceTicket(), ticketFirstDto.getIdCard(), ticketFirstDto.getEmployee(), ticketFirstDto.getCustomer(), ticketFirstDto.getId());
            }
            return new ResponseEntity<>(HttpStatus.OK);
        }

    }


    @GetMapping("/flightTicket/{id}")
    public ResponseEntity<Flight> getFlightById(@PathVariable Long id) {
        Flight flight = ticketService.findFlightById(id);
        if (flight == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(flight, HttpStatus.OK);
        }
    }

    @GetMapping("/seatTypeTicket")
    public ResponseEntity<List<SeatType>> getSeatTypeList() {
        List<SeatType> seatTypeList = ticketService.getAllSeatTypes();
        if (seatTypeList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(seatTypeList, HttpStatus.OK);
        }
    }


    @ResponseBody
    @GetMapping("/sendEmailTicket")
    public String sendEmailTicket() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(MyConstants.FRIEND_EMAIL);
        message.setSubject("thu send email");
        message.setText("nhind thấy là làm đuco rồi");
        this.mailSender.send(message);
        return "ok";
    }

}
