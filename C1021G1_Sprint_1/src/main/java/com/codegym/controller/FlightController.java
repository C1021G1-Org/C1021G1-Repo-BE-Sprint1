package com.codegym.controller;

import com.codegym.dto.FlightDto;
import com.codegym.model.Flight;
import com.codegym.service.IFlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/flight")
public class FlightController {

    @Autowired
    private IFlightService service;

    @PostMapping("/create")
    public ResponseEntity<?> createFlight(@Valid @RequestBody FlightDto flightDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors().get(0).getDefaultMessage(),HttpStatus.NOT_ACCEPTABLE);
        }
        service.createFlight(flightDto);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Flight> getId(@PathVariable Long id) {
        Flight flight = service.findById(id);
        if (flight == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(flight, HttpStatus.OK);
        }
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<?> updateFlight(@Valid @RequestBody FlightDto flightDto, BindingResult bindingResult, @PathVariable
                                          Long id) {
        flightDto.setId(id);
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors().get(0).getDefaultMessage(),HttpStatus.NOT_ACCEPTABLE);
        }
        service.updateFlight(flightDto);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

}
