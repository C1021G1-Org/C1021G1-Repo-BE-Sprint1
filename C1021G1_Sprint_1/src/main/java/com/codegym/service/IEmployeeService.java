package com.codegym.service;

import com.codegym.dto.EmployeeDto;
import com.codegym.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IEmployeeService {
    void save(EmployeeDto employeeDto);
    Employee findEmployeeById(Long id);
    Page<Employee> findAllEmployee(Pageable pageable);
}
