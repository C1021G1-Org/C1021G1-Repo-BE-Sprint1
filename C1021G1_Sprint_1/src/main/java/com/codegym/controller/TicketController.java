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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TicketController {

    @Autowired
    private ITicketService ticketService;

    @GetMapping("/listTicket")
    public ResponseEntity<Page<Ticket>> getAllListTicket(@RequestParam(defaultValue = "0") int page) {
        Page<Ticket> ticketPage = ticketService.findAllTicket(PageRequest.of(page, 10));
        if (ticketPage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(ticketPage, HttpStatus.OK);
    }

//
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<Customer> deleteCustomer(@PathVariable Long id) {
//        Customer customers = customerService.findById(id);
//        if (customers == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        customerService.remove(id);
//        return new ResponseEntity<>(customers, HttpStatus.NO_CONTENT);
//    }
//
//    @GetMapping("/search")
//    public ResponseEntity<Employee> findEmployee(String name, String code, String email) {
//        Optional<Employee> employee = iEmployeeService.findEmployee(name,code,email);
//        return employee.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
//    }

}
