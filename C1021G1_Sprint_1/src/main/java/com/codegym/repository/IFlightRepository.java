package com.codegym.repository;

import com.codegym.dto.FlightDto;
import com.codegym.model.Flight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Repository
public interface IFlightRepository extends JpaRepository<Flight, Long> {

    @Query(value = "SELECT id" +
            ", code_flight" +
            ", from_flight" +
            ", to_flight" +
            ", date_start" +
            ", date_end" +
            ", del_flag_flight" +
            ", id_airline_type " +
            "FROM flight " +
            "WHERE del_flag_flight = 1",
            nativeQuery = true,
            countQuery = "SELECT COUNT(*) " +
                    "FROM flight " +
                    "WHERE del_flag_flight = 1")
    Page<Flight> findAllFlight(Pageable pageable);

    @Query(value =" SELECT id" +
            ", code_flight"+
            ", from_flight"+
            ", to_flight"+
            ", date_start"+
            ", date_end"+
            ", del_flag_flight"+
            ", id_airline_type "+
            "FROM flight WHERE id=?", nativeQuery = true)
    FlightDto findByIdFlight(Long id);

    @Modifying
    @Query(value = "UPDATE flight SET del_flag_flight = 0 WHERE id = ?", nativeQuery = true)
    void deleteById(Long id);
}
