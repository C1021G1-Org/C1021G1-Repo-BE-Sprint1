package com.codegym.controller;

import com.codegym.common.ticket.MyConstants;
import com.codegym.dto.TicketFirstDto;
import com.codegym.dto.TicketMailDto;
import com.codegym.model.Flight;
import com.codegym.model.SeatType;
import com.codegym.model.Ticket;
import com.codegym.service.impl.TicketServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
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

                ticketService.updateFirstTicket(ticketFirstDto.getBuyerTicket(),
                        ticketFirstDto.getBirthdayTicket(), ticketFirstDto.getEmailTicket(), ticketFirstDto.getGenderTicket(),
                        ticketFirstDto.getPhoneTicket(), ticketFirstDto.getDateTicket(), ticketFirstDto.getPriceTicket(), ticketFirstDto.getIdCard(),
                        ticketFirstDto.getEmployee(), ticketFirstDto.getCustomer(), ticketFirstDto.getId());


                Ticket ticketHistory = ticketService.getTicketAddHistory(ticketFirstDto.getId());

                ticketService.addTicketHistory(ticketFirstDto.getBirthdayTicket(), ticketFirstDto.getBuyerTicket(),
                        ticketHistory.getCodeTicket(), ticketHistory.getDelFlagTicket(), ticketFirstDto.getEmailTicket(),
                        ticketFirstDto.getGenderTicket(), ticketFirstDto.getPhoneTicket(), ticketHistory.getPointTicket(),
                        ticketFirstDto.getPriceTicket(), ticketFirstDto.getStatusTicket(), ticketFirstDto.getCustomer(),
                        ticketFirstDto.getEmployee(), ticketHistory.getSeat().getId(), ticketFirstDto.getDateTicket(), ticketFirstDto.getIdCard());

            }


            return new ResponseEntity<>(ticketFirstDto, HttpStatus.OK);
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
    @PostMapping("/sendEmailTicket")
    public ResponseEntity<TicketMailDto> sendEmailTicket(@RequestBody TicketMailDto ticketMailDto) {

        MimeMessage message = mailSender.createMimeMessage();

        boolean multipart = true;

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, multipart, "utf-8");

            String htmlEmail = "<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>Title</title>\n" +
                    "<style>\n" +
                    "</style>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<div style=\"text-align: center;font-size: 30px\">\n" +
                    "    <h3 style=\"color: #ef7e06\">C1021G1Airline</h3>\n" +
                    "    <p style=\"color: brown\">Hãng hàng không C1021G1Airline chúng tôi thông báo với quý khách,về việc khách hàng đã\n" +
                    "        đăng ký sử dụng dịch vụ của hãng hàng không chúng tôi</p>\n" +
                    "    <p>quý khách đă đăng ký thành công " + ticketMailDto.getNumTicket() + " vé và tổng số tiền là" + ticketMailDto.getSumPrice() + "</p>\n" +
                    "    <p>rất cảm ơn khách hàng đã tin tưởng và sư dụng dịch vụ của chúng tôi,rất mong trong tương lai rất mong quý khách\n" +
                    "        vẩn tin tưởng sử dụng dịch vụ của chúng tôi</p>\n" +
                    "\n" +
                    "</div>\n" +
                    "\n" +
                    "\n" +
                    "</body>\n" +
                    "</html>";
            message.setContent(htmlEmail, "text/html; charset=utf-8");
            helper.setTo(MyConstants.FRIEND_EMAIL);
            helper.setSubject("C1021G1Airline thông báo đặt vé thành công");
            this.mailSender.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.OK);

    }


}
