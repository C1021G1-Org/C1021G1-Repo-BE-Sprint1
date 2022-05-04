package com.codegym.repository;

import com.codegym.model.AirlineType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAirlineTypeRepository extends JpaRepository<AirlineType, Long> {
}
