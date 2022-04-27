package com.codegym.service.impl;

import com.codegym.dto.EmployeeDto;
import com.codegym.model.Employee;
import com.codegym.repository.IEmployeeRepository;
import com.codegym.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements IEmployeeService {
    @Autowired
    private IEmployeeRepository iEmployeeRepository;
    @Override
    public void save(EmployeeDto employeeDto) {
        iEmployeeRepository.updateEmployee(employeeDto.getNameEmployee(), employeeDto.getCodeEmployee(), employeeDto.getGenderEmployee(),employeeDto.getBirthdayEmployee(), employeeDto.getPhoneEmployee(), employeeDto.getEmailEmployee(), employeeDto.getAddressEmployee(),employeeDto.getDelFlagEmployee(), employeeDto.getId());
    }

    @Override
    public Employee findEmployeeById(Long id) {
        return iEmployeeRepository.findEmployeeById(id);
    }

    @Override
    public Page<Employee> findAllEmployee(Pageable pageable) {
        return iEmployeeRepository.findAll(pageable);
    }
}
