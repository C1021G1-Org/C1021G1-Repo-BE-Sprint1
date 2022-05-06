package com.codegym.repository;

import com.codegym.dto.FlightDto;
import com.codegym.model.Flight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IFlightRepository extends JpaRepository<Flight, Long> {
    @Query(value = "select airline_type.image_airline, " +
            "flight.code_flight, " +
            "flight.date_start, " +
            "flight.date_end, " +
            "airline_type.price_airline, " +
            "flight.to_flight, " +
            "flight.from_flight " +
            "from flight " +
            "left join airline_type " +
            "on flight.id_airline_type = airline_type.id " +
            "where flight.from_flight = :from_flight " +
            "and flight.to_flight = :to_flight " +
            "and flight.date_start " +
            "like concat('%',:date_start,'%') " +
            "and flight.date_end " +
            "like concat('%',:date_end,'%') order by :sortOption ASC ",
            nativeQuery = true)
    Page<FlightDto> searchFlight(@Param("from_flight") String departureDestination,
                                 @Param("to_flight") String arrivalDestination,
                                 @Param("date_start") String departureDate,
                                 @Param("date_end") String arrivalDate,
                                 @Param("sortOption") String sortOption,
                                 Pageable pageable);

    List<Flight> findFlightsByDateStartContains(String date);

    @Query(value = "SELECT flight.id, name_airline,from_flight,to_flight,date_start,date_end,price_airline\n" +
            "FROM flight JOIN airline_type on flight.id_airline_type = airline_type.id", nativeQuery = true)
    Page<Flight> findAllFlight(Pageable pageable);

    @Query(value = "SELECT id,from_flight,to_flight,date_start,date_end,id_airline_type\n" +
            "FROM flight WHERE id = ? ", nativeQuery = true)
    Flight findByIdFlight(Long id);

    @Query(value = "UPDATE flight SET del_flag_airline = FALSE WHERE id : ?", nativeQuery = true)
    void deleteById(Long id);

}
