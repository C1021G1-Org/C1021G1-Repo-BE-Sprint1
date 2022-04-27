package com.codegym.service;

import com.codegym.model.Flight;

import java.util.Optional;

public interface IFlightService {

    Optional<Flight> findById(Long id);
    Flight createFlight(Flight flight);
}
