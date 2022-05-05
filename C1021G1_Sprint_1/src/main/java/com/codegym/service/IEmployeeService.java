package com.codegym.service;

import com.codegym.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IEmployeeService {

    Page<Employee> findAllEmployee(Pageable pageable);

    Optional<Employee> findEmployeeById(Long id);

    void deleteEmployee(Long id);

    Page<Employee> findEmployee(String name, String code, String email, Pageable pageable);

}
