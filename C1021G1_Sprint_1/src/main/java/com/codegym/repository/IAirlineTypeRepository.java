package com.codegym.repository;

import com.codegym.model.AirlineType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAirlineTypeRepository extends JpaRepository<AirlineType, Long> {
import com.codegym.model.Flight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IAirlineTypeRepository extends JpaRepository<AirlineType,Long> {
    @Query(value = "SELECT id, del_flag_airline, image_airline, name_airline, price_airline\n" +
            "FROM airline_type", nativeQuery = true)
    List<AirlineType> findAllAirlineType();
}
