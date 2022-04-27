package com.codegym.repository;

import com.codegym.model.Countries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICountriesRepository extends JpaRepository<Countries, Long> {

}
