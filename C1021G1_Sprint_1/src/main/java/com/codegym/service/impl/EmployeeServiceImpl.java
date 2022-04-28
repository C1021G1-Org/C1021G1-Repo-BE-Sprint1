package com.codegym.service.impl;

import com.codegym.dto.EmployeeDto;
import com.codegym.dto.EmployeeFindIdDto;
import com.codegym.repository.IEmployeeRepository;
import com.codegym.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements IEmployeeService {
    @Autowired
    private IEmployeeRepository iEmployeeRepository;
    @Override
    public EmployeeFindIdDto findByID(Long id) {
        return iEmployeeRepository.findEmployeeById(id);
    }

    @Override
    public void createNewEmployee(EmployeeDto employeeDto) {
        iEmployeeRepository.createEmployee(employeeDto.getAddress_Employee(),employeeDto.getCode_Employee()
                ,employeeDto.getDel_Flag_Employee(),employeeDto.getBirthday_Employee(),employeeDto.getEmail_Employee(),
                employeeDto.getGender_Employee(),employeeDto.getName_Employee(), employeeDto.getPhone_Employee(),
                employeeDto.getEmployee_Type_Id());
    }

    @Override
    public void editEmployee(EmployeeDto employeeDto) {
        iEmployeeRepository.editEmployee(employeeDto.getAddress_Employee(),employeeDto.getCode_Employee(),
                employeeDto.getDel_Flag_Employee(),employeeDto.getBirthday_Employee(),employeeDto.getEmail_Employee(),
                employeeDto.getGender_Employee(),employeeDto.getName_Employee(), employeeDto.getPhone_Employee(),
                employeeDto.getEmployee_Type_Id(),employeeDto.getId());
    }
}
