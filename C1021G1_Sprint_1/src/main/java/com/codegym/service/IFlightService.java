package com.codegym.service;

import com.codegym.model.Flight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface IFlightService {
   Map<String,Page<Flight>> searchFlight(String fromFlight, String toFlight, String dateStart,
                                          String dateEnd, Pageable pageable);

}
