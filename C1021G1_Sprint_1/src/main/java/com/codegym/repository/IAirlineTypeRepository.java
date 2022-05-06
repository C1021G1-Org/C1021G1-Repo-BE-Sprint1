package com.codegym.repository;

import com.codegym.model.AirlineType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IAirlineTypeRepository extends JpaRepository<AirlineType,Long> {

    @Query(value = "SELECT id, " +
            "del_flag_airline, " +
            "image_airline, " +
            "name_airline, " +
            "price_airline " +
            "FROM airline_type", nativeQuery = true)
    List<AirlineType> findAllAirlineType();

    @Query(value = "SELECT id, " +
            "del_flag_airline, " +
            "image_airline, " +
            "name_airline, " +
            "price_airline " +
            "FROM airline_type " +
            "WHERE id = ?", nativeQuery = true)
    Optional<AirlineType> findById(Long id);
}
