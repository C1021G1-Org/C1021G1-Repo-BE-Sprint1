package com.codegym.service.impl;

import com.codegym.model.Flight;
import com.codegym.repository.IFlightRepository;
import com.codegym.service.IFlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class FlightServiceImpl implements IFlightService {
    @Autowired
    private IFlightRepository iFlightRepository;
    @Override
    public Page<Flight> searchFlight(String fromFlight, String toFlight, String dateStart,
                                                 String dateEnd, Pageable pageable) {
        Map<String,Page<Flight>> searchFlight = new HashMap<>();
        searchFlight.put("Một chiều",iFlightRepository.searchFlight(fromFlight,toFlight,dateStart,dateEnd,pageable));
        searchFlight.put("Hai chiều",iFlightRepository.searchFlight(fromFlight,toFlight,dateStart,dateEnd,pageable));

    };
}
