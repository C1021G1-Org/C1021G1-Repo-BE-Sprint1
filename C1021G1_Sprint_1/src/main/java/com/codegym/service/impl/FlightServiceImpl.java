package com.codegym.service.impl;

import com.codegym.dto.FlightDto;
import com.codegym.dto.IFlightDto;
import com.codegym.model.Flight;
import com.codegym.repository.IFlightRepository;
import com.codegym.service.IFlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;


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

    @Override
    public Page<Flight> findAllFlight(Pageable pageable) {
        return repository.findAllFlight(pageable);
    }

    @Override
    public Page<Flight> findAllFlightNotPage(Pageable pageable) {
        return repository.findAllFlightNotPage(pageable);
    }

    @Override
    public IFlightDto findById1(Long id) {
        return repository.findByIdFlight1(id);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Page<Flight> searchFlight(String fromFlight,String toFlight,String dateStart,String dateEnd,Pageable pageable) {
        return repository.searchAllByFields(fromFlight, toFlight, dateStart, dateEnd, pageable);
    }
}
