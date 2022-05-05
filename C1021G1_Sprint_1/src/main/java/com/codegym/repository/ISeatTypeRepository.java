package com.codegym.repository;

import com.codegym.model.SeatType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISeatTypeRepository extends JpaRepository<SeatType,Long> {
}
