package com.codegym.repository;

import com.codegym.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFlightRepository extends JpaRepository<Flight,Long> {
}
