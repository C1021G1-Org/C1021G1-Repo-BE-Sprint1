package com.codegym.controller;

import com.codegym.dto.FlightDto;
import com.codegym.model.Flight;
import com.codegym.model.FormSearch;
import com.codegym.service.IFlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/flight")
public class FlightController {

    @Autowired
    private IFlightService iFlightService;

    Pageable pageable = PageRequest.of(0, 10);

    Map<String, Page<FlightDto>> searchFlight;

    @GetMapping("/search")
    public ResponseEntity<?> searchFlight(@RequestBody FormSearch formSearch) {
        if (formSearch.getSearchOption().equals("oneway")) {
            if (formSearch.getFromFlight() == null || formSearch.getFromFlight() == "" || formSearch.getDateStart() == null ||
                formSearch.getDateStart() == "" || formSearch.getToFlight() == null || formSearch.getToFlight() == ""
                    || formSearch.getToFlight() == "") {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            searchFlight = iFlightService.searchFlight(formSearch.getFromFlight(), formSearch.getToFlight(), formSearch.getDateStart(),
                    formSearch.getDateEnd(), pageable);
            if (searchFlight.get("oneway").getSize() == 0) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(searchFlight.get("oneway").getContent(), HttpStatus.OK);
            }
        } else if (formSearch.getSearchOption().equals("twoway")) {
            if (formSearch.getFromFlight() == null || formSearch.getFromFlight() == "" || formSearch.getDateStart() == null ||
                    formSearch.getDateStart() == "" || formSearch.getToFlight() == null || formSearch.getToFlight() == ""
            || formSearch.getToFlight() == "" || formSearch.getDateEnd() == null || formSearch.getDateEnd() == "") {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            searchFlight = iFlightService.searchFlight(formSearch.getFromFlight(), formSearch.getToFlight(), formSearch.getDateStart(),
                    formSearch.getDateEnd(), pageable);
            if (searchFlight.get("oneway").getSize() == 0 && searchFlight.get("twoway").getSize() == 0) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<>(searchFlight.get("twoway").getContent().size(), HttpStatus.OK);
    }
}

