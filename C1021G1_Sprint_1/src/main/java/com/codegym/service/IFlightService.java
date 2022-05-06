package com.codegym.service;


import com.codegym.dto.FlightDto;
import com.codegym.model.Flight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface IFlightService {
    Map<String, Page<FlightDto>> searchFlight(String departureDestination, String arrivalDestination, String departureDate, String arrivalDate, String sortOption, Pageable pageable);

    Page<Flight> findAllFlight(Pageable pageable);

    List<Flight> searchFlightByDate(String date);

    Flight findById(Long id);

    void deleteById(Long id);
}
