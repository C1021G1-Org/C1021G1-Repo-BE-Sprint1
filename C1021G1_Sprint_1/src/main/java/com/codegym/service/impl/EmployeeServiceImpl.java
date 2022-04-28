package com.codegym.service.impl;

import com.codegym.model.Employee;
import com.codegym.repository.IEmployeeRepository;
import com.codegym.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

//    @Autowired
//    IEmployeeRepository iEmployeeRepository;
//
//    @Override
//    public Page<Employee> findAllEmployee(Pageable pageable) {
//        return iEmployeeRepository.findAllEmployee(pageable);
//    }

//    @Override
//    public Optional<Employee> findEmployeeById(Long id) {
//        return iEmployeeRepository.findEmployeeById(id);
//    }

//    @Override
//    public void deleteEmployee(Long id) {
//        iEmployeeRepository.deleteEmployee(id);
//    }
}
