package com.codegym.service;

import com.codegym.model.AirlineType;
import com.codegym.model.Flight;
import com.codegym.repository.IAirlineTypeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IAirlineTypeService {

    List<AirlineType> getAll();
    List<AirlineType> findAll();
}
