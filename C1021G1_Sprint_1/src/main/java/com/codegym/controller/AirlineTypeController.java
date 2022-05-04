package com.codegym.controller;

import com.codegym.model.AirlineType;
import com.codegym.model.Flight;
import com.codegym.service.IAirlineTypeService;
import com.codegym.service.IFlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("api/flight")
@RestController
@CrossOrigin("*")
public class AirlineTypeController {
    @Autowired
    private IAirlineTypeService airlineTypeService;

    @GetMapping("/listAirlineType")
    public ResponseEntity<List<AirlineType>> listAll() {
        List<AirlineType> airlineTypeList = airlineTypeService.findAll();
        if (airlineTypeList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(airlineTypeList, HttpStatus.OK);
    }


}
