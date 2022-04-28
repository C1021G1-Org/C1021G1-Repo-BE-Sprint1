package com.codegym.service.impl;

import com.codegym.model.Flight;
import com.codegym.repository.IFlightRepository;
import com.codegym.service.IFlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.Map;



@Service
public class FlightServiceImpl implements IFlightService {
    @Autowired
    private IFlightRepository iFlightRepository;
    private Map<String, Page<Flight>> searchFlight;
    private Page<Flight> departure;
    private Page<Flight> arrival;
    @Override
    public Map<String, Page<Flight>> searchFlight(String departureDestination, String arrivalDestination, String departureDate, String arrivalDate, Pageable pageable) {
        departure = iFlightRepository.searchFlight(departureDestination, arrivalDestination, departureDate, arrivalDate, pageable);
        searchFlight.put("oneway", departure);
        arrival = iFlightRepository.searchFlight(arrivalDestination, departureDestination, departureDate, arrivalDate, pageable);
        searchFlight.put("twoway", arrival);
        return searchFlight;
    }
}
