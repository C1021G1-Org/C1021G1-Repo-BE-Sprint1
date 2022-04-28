package com.codegym.service.impl;

import com.codegym.dto.FlightDto;
import com.codegym.model.Flight;
import com.codegym.repository.IFlightRepository;
import com.codegym.service.IFlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;



import java.util.HashMap;
import java.util.Map;

@Service
public class FlightServiceImpl implements IFlightService {

    @Autowired
    private IFlightRepository iFlightRepository;

    Map<String, Page<FlightDto>> searchFlight = new HashMap<>();
    Page departure;
    Page arrival;

    @Override
    public Map<String, Page<FlightDto>> searchFlight(String departureDestination, String arrivalDestination, String departureDate, String arrivalDate, Pageable pageable) {
        departure = iFlightRepository.searchFlight(departureDestination, arrivalDestination, departureDate, arrivalDate, pageable);
        searchFlight.put("oneway", departure);
        arrival = iFlightRepository.searchFlight(arrivalDestination, departureDestination, departureDate, arrivalDate, pageable);
        searchFlight.put("twoway", arrival);
        return searchFlight;
    }

    @Override
    public Page<Flight> findAllFlight(Pageable pageable) {
        return iFlightRepository.findAllFlight(pageable);
    }

    @Override
    public Flight findById(Long id) {
        return iFlightRepository.findByIdFlight(id);
    }

    @Override
    public void deleteById(Long id) {
        iFlightRepository.deleteById(id);
    }
}
