package com.codegym.service;

import com.codegym.dto.FlightDto;
import com.codegym.dto.FlightSearchDto;
import com.codegym.dto.IFlightDto;
import com.codegym.model.Flight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface IFlightService {
    Map<String, Page<FlightSearchDto>> searchFlight(String departureDestination, String arrivalDestination, String departureDate, String arrivalDate, String sortOption, Pageable pageable);

    List<Flight> searchFlightByDate(String date);

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
