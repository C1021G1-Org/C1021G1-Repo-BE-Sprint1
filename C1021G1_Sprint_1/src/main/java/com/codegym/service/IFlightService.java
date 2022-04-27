package com.codegym.service;

import com.codegym.model.Flight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IFlightService {
    Page<Flight> findAllFlight(Pageable pageable);

    Flight findById(Long id);

    void deleteById(Long id);
}
