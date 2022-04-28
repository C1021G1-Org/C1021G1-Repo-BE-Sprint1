package com.codegym.service;


import com.codegym.dto.EmployeeDto;
import com.codegym.dto.EmployeeFindIdDto;

public interface IEmployeeService {
    EmployeeFindIdDto findByID(Long id);
    void createNewEmployee(EmployeeDto employeeDto) ;
    void editEmployee(EmployeeDto employeeDto );
}
