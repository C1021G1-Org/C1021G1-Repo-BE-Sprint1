package com.codegym.controller;

import com.codegym.model.Flight;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.Map;

@SpringBootTest
public class FlightRestController_getListFlight {

    @Autowired
    private FlightController flightController;

    @Test
    public void getListStudent_1_1(){
        ResponseEntity<Map<String, Page<Flight>>> response =
                this.flightController.getFlight(null,"","","", Pageable.unpaged());
        Assertions.assertEquals(404, response.getStatusCodeValue());
    }
    @Test
    public void getListStudent_1_2(){
        ResponseEntity<Map<String, Page<Flight>>> response =
                this.flightController.getFlight("",null,"","", Pageable.unpaged());
        Assertions.assertEquals(404, response.getStatusCodeValue());
    }
    @Test
    public void getListStudent_1_3(){
        ResponseEntity<Map<String, Page<Flight>>> response =
                this.flightController.getFlight("","",null,"", Pageable.unpaged());
        Assertions.assertEquals(404, response.getStatusCodeValue());
    }
    @Test
    public void getListStudent_1_4(){
        ResponseEntity<Map<String, Page<Flight>>> response =
                this.flightController.getFlight("","","",null, Pageable.unpaged());
        Assertions.assertEquals(404, response.getStatusCodeValue());
    }
}
