package com.codegym.service;

import com.codegym.dto.FlightDto;
import com.codegym.model.Flight;

import java.util.List;

public interface IFlightService {

    Flight findById(Long id);

    void createFlight(FlightDto flightDto);

    void updateFlight(FlightDto flightDto);
}
