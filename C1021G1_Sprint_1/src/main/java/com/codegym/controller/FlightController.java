package com.codegym.controller;

import com.codegym.model.Flight;
import com.codegym.service.IFlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin("*")
public class FlightController {
    @Autowired
    private IFlightService iFlightService;
    @GetMapping("search-flight")
    public ResponseEntity<Map<String, Page<Flight>>> getFlight(@PathVariable String fromFlight, String toFlight, String dateStart,
                                                               String dateEnd, Pageable pageable){

    }
}
