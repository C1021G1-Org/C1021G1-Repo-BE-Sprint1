package com.codegym.controller;

import com.codegym.model.Flight;
import com.codegym.service.IFlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Map;

@RestController
@CrossOrigin("*")
public class FlightController {
    @Autowired
    private IFlightService iFlightService;
    @GetMapping("search-flight")
    public ResponseEntity<Map<String, Page<Flight>>> getFlight(@RequestParam("fromFlight") String fromFlight,
                                                               @RequestParam("toFlight")String toFlight,
                                                               @RequestParam("dateStart") String dateStart,
                                                               @RequestParam("dateEnd")String dateEnd,
                                                               Pageable pageable){
        Map<String,Page<Flight>> map = iFlightService.searchFlight(fromFlight,toFlight,dateStart,dateEnd,pageable);

    }
}
