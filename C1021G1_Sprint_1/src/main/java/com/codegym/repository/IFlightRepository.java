package com.codegym.repository;

import com.codegym.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IFlightRepository extends JpaRepository<Flight, Long> {

//    @Query(value = "INSERT INTO ")
}
