package com.codegym.controller;

import com.codegym.model.Flight;
import com.codegym.service.IFlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class FlightController {
    @Autowired
    private IFlightService flightService;

    @GetMapping("/list-flight")
    public ResponseEntity<Page<Flight>> listAllFlight(Pageable pageable) {
        Page<Flight> flightPage = flightService.findAllFlight(pageable);
        if (flightPage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(flightPage, HttpStatus.OK);
    }

    @RequestMapping(value = "/flight/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Flight> deleteFlight(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting Customer with id " + id);

        Flight flight = flightService.findById(id);
        if (flight == null) {
            System.out.println("Unable to delete. Employee with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        flightService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
