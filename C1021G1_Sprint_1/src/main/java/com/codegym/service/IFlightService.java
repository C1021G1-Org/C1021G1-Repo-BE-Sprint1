package com.codegym.service;

import com.codegym.dto.FlightDto;
import com.codegym.model.Flight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IFlightService {
    Page<Flight> findAllFlight(Pageable pageable);

    FlightDto  findById(Long id);

    void deleteById(Long id);
}
