package com.codegym.controller;

import com.codegym.dto.FlightDto;
import com.codegym.dto.FlightDtoCheck;
import com.codegym.dto.IFlightDto;
import com.codegym.model.Flight;
import com.codegym.service.IFlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.codegym.model.AirlineType;
import com.codegym.service.IAirlineTypeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/flight")
public class FlightController {
    @Autowired
    private IFlightService flightService;
    @Autowired
    private IAirlineTypeService airlineTypeService;

    @GetMapping("/search")
    public ResponseEntity<Page<Flight>> searchFlight(@RequestParam(defaultValue = "") String fromFlight,String toFlight,String dateStart,String dateEnd,
                                                       @RequestParam(defaultValue = "0") int page){

        Page<Flight> flightList = flightService.searchFlight(fromFlight,toFlight,dateStart,dateEnd,PageRequest.of(page, 10));

        if (flightList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(flightList, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<Page<Flight>> getListFlight(@RequestParam(defaultValue = "0") int page) {
        Page<Flight> flightPage = flightService.findAllFlight(PageRequest.of(page, 10));
        if (flightPage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(flightPage, HttpStatus.OK);
    }

    @GetMapping("/list-not-pagination")
    public ResponseEntity<Page<Flight>> getListFlightNotPagination(Pageable pageable) {
        Page<Flight> flightPage = flightService.findAllFlightNotPage(pageable);
        if (flightPage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(flightPage, HttpStatus.OK);
    }

    @GetMapping("/find-id/{id}")
    public ResponseEntity<Flight> findById(@PathVariable Long id) {
        IFlightDto flightDto = flightService.findById1(id);
        AirlineType airlineType = airlineTypeService.findById(flightDto.getId_airline_type());
        System.out.println(flightDto.getId());

        if (flightDto == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        Flight flight = new Flight();
        flight.setId(id);
        flight.setCodeFlight(flightDto.getCode_flight());
        flight.setFromFlight(flightDto.getFrom_flight());
        flight.setToFlight(flightDto.getTo_flight());
        flight.setDateStart(flightDto.getDate_start());
        flight.setDateEnd(flightDto.getDate_end());
        flight.setDelFlagFlight(flightDto.getDel_flag_flight());
        flight.setAirlineType(airlineType);
        System.out.println(flight);
        return new ResponseEntity<>(flight, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Flight> getDeleteFlight(@PathVariable Long id) {
        System.out.println("Fetching & Deleting Flight with id " + id);

        IFlightDto flight = flightService.findById1(id);

        if (flight == null) {
            System.out.println("Unable to delete. Flight with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        flightService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
//    tronghd create chuyến bay
    @PostMapping("/create")
    public ResponseEntity<?> createFlight(@Valid @RequestBody FlightDto flightDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors().get(0).getDefaultMessage(),HttpStatus.NOT_ACCEPTABLE);
        }
        flightService.createFlight(flightDto);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }   

    @GetMapping("/{id}")
    public ResponseEntity<Flight> getId(@PathVariable Long id) {
        Flight flight = flightService.findById(id);
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
        flightService.updateFlight(flightDto);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

}

