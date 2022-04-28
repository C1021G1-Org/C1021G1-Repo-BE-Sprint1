package com.codegym.repository;

import com.codegym.model.Flight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface IFlightRepository extends JpaRepository<Flight,Long> {
    @Query(value = "select " +
            "airline_type.image_airline, " +
            "flight.code_flight, " +
            "flight.date_start, " +
            "flight.date_end, " +
            "airline_type.price_airline " +
            "from flight " +
            "left join airline_type " +
            "on flight.id_airline_type = airline_type.id " +
            "where flight.from_flight = :from_flight " +
            "and flight.to_flight = :to_flight " +
            "and flight.date_start like concat('%',:date_start,'%') " +
            "and flight.date_end like concat('%',:date_end,'%')",
            nativeQuery = true)
    Page<Flight> searchFlight(@Param("from_flight") String departureDestination,
                                 @Param("to_flight") String arrivalDestination,
                                 @Param("date_start") String departureDate,
                                 @Param("date_end") String arrivalDate,
                                 Pageable pageable);
}
