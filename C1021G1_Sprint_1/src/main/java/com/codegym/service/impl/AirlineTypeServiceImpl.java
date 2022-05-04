package com.codegym.service.impl;

import com.codegym.model.AirlineType;
import com.codegym.repository.IAirlineTypeRepository;
import com.codegym.service.IAirlineTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirlineTypeServiceImpl implements IAirlineTypeService {

    @Autowired
    private IAirlineTypeRepository repository;

    @Override
    public List<AirlineType> getAll() {
        return repository.findAll();
    }
}
