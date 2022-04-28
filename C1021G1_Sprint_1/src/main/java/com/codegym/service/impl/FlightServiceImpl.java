package com.codegym.service.impl;

import com.codegym.dto.FlightDto;
import com.codegym.model.Flight;
import com.codegym.repository.IFlightRepository;
import com.codegym.service.IFlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlightServiceImpl implements IFlightService {

    @Autowired
    private IFlightRepository repository;

    @Override
    public Flight findById(Long id) {
        return repository.findByIdFlight(id);
    }

    @Override
    public void createFlight(FlightDto flightDto) {
        repository.createFlight(flightDto.getCodeFlight(), flightDto.getFromFlight(),
                flightDto.getToFlight(), flightDto.getDateStart(), flightDto.getDateEnd(),
                flightDto.getAirlineType(), true);
    }

    @Override
    public void updateFlight(FlightDto flightDto) {
        repository.updateFlight(flightDto.getCodeFlight(), flightDto.getFromFlight(), flightDto.getToFlight(), flightDto.getDateStart(),
                flightDto.getDateEnd(), flightDto.getAirlineType(), true, flightDto.getId());
    }
}
