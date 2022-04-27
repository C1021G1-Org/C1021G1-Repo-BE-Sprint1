package com.codegym.service.impl;

import com.codegym.model.Flight;
import com.codegym.repository.IFlightRepository;
import com.codegym.service.IFlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FlightServiceImpl implements IFlightService {
    @Autowired
    private IFlightRepository iFlightRepository;

    @Override
    public Page<Flight> findAllFlight(Pageable pageable) {
        return iFlightRepository.findAllFlight(pageable);
    }

    @Override
    public Flight findById(Long id) {
        return iFlightRepository.findByIdFlight(id);
    }

    @Override
    public void deleteById(Long id) {
        iFlightRepository.deleteById(id);
    }
}
