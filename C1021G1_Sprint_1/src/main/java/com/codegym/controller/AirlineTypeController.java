package com.codegym.controller;

import com.codegym.model.AirlineType;
import com.codegym.service.IAirlineTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/flight")
public class AirlineTypeController {

    @Autowired
    private IAirlineTypeService service;

    @GetMapping("/listAirlineType")
    public ResponseEntity<List<AirlineType>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }
import com.codegym.model.Flight;
import com.codegym.service.IAirlineTypeService;
import com.codegym.service.IFlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public class AirlineTypeController {
    @Autowired
    private IAirlineTypeService airlineTypeService;

    @GetMapping("/list-flight")
    public ResponseEntity<List<AirlineType>> listAll() {
        List<AirlineType> airlineTypeList = airlineTypeService.findAll();
        if (airlineTypeList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(airlineTypeList, HttpStatus.OK);
    }


}
