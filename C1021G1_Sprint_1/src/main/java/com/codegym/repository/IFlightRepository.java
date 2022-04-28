package com.codegym.repository;

import com.codegym.model.Flight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface IFlightRepository extends JpaRepository<Flight,Long> {
    @Query(value = "select  flight.code_fligh, flight.date_start, flight.date_end, " +
            "airline_type.price_airline,airline_type.image_airline from flight left join airline_type on flight.id_airline_type = airline_type.id" +
            "where flight.from_flight = ?1 and flight.to_flight = ?2 and flight.date_start like ?3% and flight.date_end like ?4% ", nativeQuery = true)
    Page<Flight> searchFlight(String fromFlight, String toFlight, String dateStart,
                                          String dateEnd, Pageable pageable);
}
