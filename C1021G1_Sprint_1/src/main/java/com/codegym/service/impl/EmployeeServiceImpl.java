package com.codegym.service.impl;

import com.codegym.model.Employee;
import com.codegym.dto.EmployeeDto;
import com.codegym.dto.EmployeeFindIdDto;
import com.codegym.repository.IEmployeeRepository;
import com.codegym.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    IEmployeeRepository iEmployeeRepository;

    @Override
    public Page<Employee> findAllEmployee(Pageable pageable) {
        return iEmployeeRepository.findAllEmployee(pageable);
    }

    @Override
    public Optional<Employee> findEmployeeById(Long id) {
        return iEmployeeRepository.findEmployeeById(id);
    }

    public void deleteEmployee(Long id) {
        iEmployeeRepository.deleteEmployee(id);
    }

    @Override
    public Page<Employee> findEmployee(String name, String code, String email, Pageable pageable) {
        return iEmployeeRepository.findEmployeeByElementContaining(name, code, email, pageable) ;
    }
}
