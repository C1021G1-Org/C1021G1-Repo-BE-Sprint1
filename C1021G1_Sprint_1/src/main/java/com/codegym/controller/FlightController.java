package com.codegym.controller;

import com.codegym.model.Flight;
import com.codegym.service.IFlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Map;

@RestController
@CrossOrigin("*")
public class FlightController {
    @Autowired
    private IFlightService iFlightService;
    Map<String, Page<Flight>> searchFlight;

    @GetMapping("/search")
    public ResponseEntity<?> searchFlight(String searchOption, @RequestParam("from_flight") String departureDestination,
                                          @RequestParam("to_flight") String arrivalDestination,
                                          @RequestParam("date_start") String departureDate,
                                          @RequestParam("date_end") String arrivalDate, Pageable pageable) {
        if (searchOption.equals("oneway")) {
            searchFlight = iFlightService.searchFlight(departureDestination, arrivalDestination, departureDate, arrivalDate, pageable);
            if (searchFlight.get("oneway").getSize() == 0) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(searchFlight.get("oneway").getContent(), HttpStatus.OK);
            }
        } else if (searchOption.equals("twoway")) {
            searchFlight = iFlightService.searchFlight(departureDestination, arrivalDestination, departureDate, arrivalDate, pageable);
            if (searchFlight.get("oneway").getSize() == 0 && searchFlight.get("twoway").getSize() == 0) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<>(searchFlight.get("twoway").getContent().size(), HttpStatus.OK);
    }
}
