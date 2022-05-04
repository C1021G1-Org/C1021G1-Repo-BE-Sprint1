package com.codegym.controller;

import com.codegym.dto.FlightDto;
import com.codegym.dto.FlightDtoCheck;
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

//    tronghd create chuyến bay
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

//    tronghd update chuyến bay
    @PatchMapping("/update/{id}")
    public ResponseEntity<?> updateFlight(@Valid @RequestBody FlightDtoCheck flightDtoCheck,
                                          BindingResult bindingResult, @PathVariable Long id) {
        FlightDto flightDto = new FlightDto();
        flightDto.setId(id);
        flightDto.setCodeFlight(flightDtoCheck.getCodeFlight());
        flightDto.setFromFlight(flightDtoCheck.getFromFlight());
        flightDto.setToFlight(flightDtoCheck.getToFlight());
        flightDto.setDateStart(flightDtoCheck.getDateStart());
        flightDto.setDateEnd(flightDtoCheck.getDateEnd());
        flightDto.setAirlineType(flightDtoCheck.getAirlineType().getId());
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors().get(0).getDefaultMessage(),HttpStatus.NOT_ACCEPTABLE);
        }
        service.updateFlight(flightDto);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
}
