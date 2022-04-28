package com.codegym.controller;

import com.codegym.dto.FlightDto;
import com.codegym.model.Flight;
import com.codegym.service.IFlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("api/flight")
public class FlightController {
    @Autowired
    private IFlightService flightService;

    @GetMapping("/list")
    public ResponseEntity<Page<Flight>> getListFlight(Pageable pageable) {
        Page<Flight> flightPage = flightService.findAllFlight(pageable);
        if (flightPage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(flightPage, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Flight> getDeleteFlight(@PathVariable Long id) {
        System.out.println("Fetching & Deleting Flight with id " + id);

        FlightDto flight = flightService.findById(id);

        if (flight == null) {
            System.out.println("Unable to delete. Flight with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        flightService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
