package com.codegym.service.impl;

import com.codegym.model.Flight;
import com.codegym.repository.IFlightRepository;
import com.codegym.service.IFlightService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class FlightServiceImpl implements IFlightService {

    @Autowired
    private IFlightRepository repository;

    @Override
    public Optional<Flight> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Flight createFlight(Flight flight) {
        return null;
    }
}
