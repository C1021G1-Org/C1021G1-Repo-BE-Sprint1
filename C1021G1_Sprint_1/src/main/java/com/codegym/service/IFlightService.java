package com.codegym.service;


import com.codegym.dto.FlightDto;
import com.codegym.model.Flight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Map;

import java.util.List;
import java.util.Optional;

public interface IFlightService {

    Page<Flight> findAllFlight(Pageable pageable);

    Page<Flight> findAllFlightNotPage(Pageable pageable);

    FlightDto  findById(Long id);

    void deleteById(Long id);

    Page<Flight> searchFlight(String keyword1,String keyword2,String keyword3,String keyword4, Pageable pageable);
}
