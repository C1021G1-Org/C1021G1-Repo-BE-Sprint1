package com.codegym.service;

import com.codegym.dto.FlightDto;
import com.codegym.model.Flight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Map;

public interface IFlightService {
    Map<String, Page<FlightDto>> searchFlight(String departureDestination, String arrivalDestination, String departureDate, String arrivalDate, Pageable pageable);
}
