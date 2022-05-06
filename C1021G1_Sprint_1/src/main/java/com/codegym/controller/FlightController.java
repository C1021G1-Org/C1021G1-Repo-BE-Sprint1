package com.codegym.controller;


import com.codegym.dto.FlightDto;
import com.codegym.model.Flight;
import com.codegym.model.FormSearch;
import com.codegym.service.IFlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RequestMapping("/api/flight")

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

    Pageable pageable = PageRequest.of(0, 10);

    Map<String, Page<FlightDto>> searchFlight;

    @PostMapping("/search")
    public ResponseEntity<?> searchFlight(@RequestBody FormSearch formSearch) {
        System.out.println(formSearch);
        if (formSearch.getSearchOption().equals("oneway")) {
            if (formSearch.getFromFlight() == null
                    || formSearch.getFromFlight().equals("")
                    || formSearch.getDateStart() == null
                    || formSearch.getDateStart().equals("")
                    || formSearch.getToFlight() == null
                    || formSearch.getToFlight().equals("")
                    || formSearch.getDateEnd() == null
                    || formSearch.getDateEnd().equals("")) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            switch (formSearch.getSortOption()) {
                case "price_airline":
                    searchFlight = flightService.searchFlight(formSearch.getFromFlight(), formSearch.getToFlight(), formSearch.getDateStart(),
                            formSearch.getDateEnd(), "price_airline", pageable);
                    break;
                case "date_start":
                    searchFlight = flightService.searchFlight(formSearch.getFromFlight(), formSearch.getToFlight(), formSearch.getDateStart(),
                            formSearch.getDateEnd(), "date_start" , pageable);
                    break;
                case "image_airline":
                    searchFlight = flightService.searchFlight( formSearch.getFromFlight(), formSearch.getToFlight(), formSearch.getDateStart(),
                            formSearch.getDateEnd(), "image_airline", pageable);
                    break;
                default:
                    searchFlight = flightService.searchFlight( formSearch.getFromFlight(), formSearch.getToFlight(), formSearch.getDateStart(),
                            formSearch.getDateEnd(), "from_flight", pageable);
            }
            if (searchFlight.get("oneway").getSize() == 0) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(searchFlight, HttpStatus.OK);
            }
        } else if (formSearch.getSearchOption().equals("twoway")) {
            if (formSearch.getFromFlight() == null
                    || formSearch.getFromFlight().equals("")
                    || formSearch.getDateStart() == null
                    || formSearch.getDateStart().equals("")
                    || formSearch.getToFlight() == null
                    || formSearch.getToFlight().equals("")
                    || formSearch.getDateEnd() == null
                    || formSearch.getDateEnd().equals("")) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            switch (formSearch.getSortOption()) {
                case "price_airline":
                    searchFlight = flightService.searchFlight(formSearch.getFromFlight(), formSearch.getToFlight(), formSearch.getDateStart(),
                            formSearch.getDateEnd(), "price_airline", pageable);
                    break;
                case "date_start":
                    searchFlight = flightService.searchFlight(formSearch.getFromFlight(), formSearch.getToFlight(), formSearch.getDateStart(),
                            formSearch.getDateEnd(), "date_start" , pageable);
                    break;
                case "image_airline":
                    searchFlight = flightService.searchFlight( formSearch.getFromFlight(), formSearch.getToFlight(), formSearch.getDateStart(),
                            formSearch.getDateEnd(), "image_airline", pageable);
                    break;
                default:
                    searchFlight = flightService.searchFlight( formSearch.getFromFlight(), formSearch.getToFlight(), formSearch.getDateStart(),
                            formSearch.getDateEnd(), "from_flight", pageable);
            }
            if (searchFlight.get("oneway").getSize() == 0 && searchFlight.get("twoway").getSize() == 0) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(searchFlight, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping ("/searchByDate")
    public ResponseEntity<List<Flight>> searchFlightByDate(@RequestParam (required = false) String date) {

        List<Flight> flightPage = flightService.searchFlightByDate(date);
        return new ResponseEntity<>(flightPage, HttpStatus.OK);
    }
}

