package com.codegym.service;

import com.codegym.model.Flight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IFlightService extends JpaRepository<Flight, Long> {

    @Query(value = "select airline_type.image_airline, flight.code_flight, flight.date_start, flight.date_end, " +
            "airline_type.price_airline from flight left join airline_type on flight.id_airline_type = airline_type.id" +
            "where flight.from_flight = ?1 and flight.to_flight = ?2 and flight.date_start like ?3% and flight.date_end like ?4% ", nativeQuery = true)
    public Page<Flight> searchFlight(String departureDestination, String arrivalDestination, String departureDate,
                                     String arrivalDate, Pageable pageable);

}
