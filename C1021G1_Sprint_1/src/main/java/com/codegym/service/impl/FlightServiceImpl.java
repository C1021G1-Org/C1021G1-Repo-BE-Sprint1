package com.codegym.service.impl;

import com.codegym.dto.FlightDto;
import com.codegym.dto.FlightSearchDto;
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
import java.util.List;
import java.util.Map;

@Service
public class FlightServiceImpl implements IFlightService {
    Map<String, Page<FlightSearchDto>> searchFlightList = new HashMap<>();
    Page departure;
    Page arrival;

    @Autowired
    private IFlightRepository repository;

    @Override
    public Map<String, Page<FlightSearchDto>> searchFlight(String departureDestination, String arrivalDestination, String departureDate, String arrivalDate, String sortOption, Pageable pageable) {
        departure = repository.searchFlight(departureDestination, arrivalDestination, departureDate, arrivalDate, sortOption, pageable);
        searchFlightList.put("oneway", departure);
        arrival = repository.searchFlight(arrivalDestination, departureDestination, departureDate, arrivalDate, sortOption, pageable);
        searchFlightList.put("twoway", arrival);
        return searchFlightList;
    }

    public Flight findById(Long id) {
        return repository.findByIdFlight(id);
    }

    @Override
    public Integer findByCodeFlight(String codeFlight) {
        return repository.finByCodeFlight(codeFlight);
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
    public List<Flight> searchFlightByDate(String date) {
        return repository.findFlightsByDateStartContains(date);
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
