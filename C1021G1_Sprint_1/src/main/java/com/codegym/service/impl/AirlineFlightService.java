package com.codegym.service.impl;

import com.codegym.model.AirlineType;
import com.codegym.model.Flight;
import com.codegym.repository.IAirlineTypeRepository;
import com.codegym.service.IAirlineTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AirlineFlightService implements IAirlineTypeService {
    @Autowired
    private IAirlineTypeRepository iAirlineTypeRepository;

    @Override
    public List<AirlineType> findAll() {
        return iAirlineTypeRepository.findAllAirlineType();
    }
}
