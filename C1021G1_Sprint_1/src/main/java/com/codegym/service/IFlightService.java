package com.codegym.service;

import com.codegym.dto.FlightDto;
import com.codegym.dto.IFlightDto;
import com.codegym.model.Flight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IFlightService {

    Flight findById(Long id);

    Integer findByCodeFlight(String codeFlight);

    void createFlight(FlightDto flightDto);

    void updateFlight(FlightDto flightDto);

    Page<Flight> findAllFlight(Pageable pageable);

    Page<Flight> findAllFlightNotPage(Pageable pageable);

    IFlightDto findById1(Long id);


    void deleteById(Long id);

    Page<Flight> searchFlight(String fromFlight,String toFlight,String dateStart,String dateEnd,Pageable pageable);
}
