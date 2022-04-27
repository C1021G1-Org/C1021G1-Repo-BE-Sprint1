package com.codegym.repository;

import com.codegym.model.Flight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IFlightRepository extends JpaRepository<Flight,Long> {
    @Query(value = "SELECT flight.id, name_airline,from_flight,to_flight,date_start,date_end,price_airline\n" +
            "FROM flight JOIN airline_type on flight.id_airline_type = airline_type.id", nativeQuery = true)
    Page<Flight> findAllFlight(Pageable pageable);

    @Query(value = "DELETE FROM flight where id=?", nativeQuery = true)
    void deleteById(Long id);
}
