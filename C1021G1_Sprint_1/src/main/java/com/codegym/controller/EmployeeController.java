package com.codegym.controller;

import com.codegym.dto.EmployeeDto;
import com.codegym.dto.EmployeeFindIdDto;
import com.codegym.service.IEmployeeService;

import com.codegym.validation.EmployeeCreateByRequestDtoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class EmployeeController {
@Autowired
    private IEmployeeService iEmployeeService;
    @Autowired
    private EmployeeCreateByRequestDtoValidator employeeCreateByRequestDtoValidator;
    @GetMapping("/find-id/{id}")
    public ResponseEntity<EmployeeFindIdDto> findById(@PathVariable Long id) {
        System.out.println(id);
        EmployeeFindIdDto employee = iEmployeeService.findByID(id);

        if (employee == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        System.out.println(employee);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }
    @PostMapping("/createEmployee")
    public ResponseEntity<?> createEmployee(@Valid @RequestBody EmployeeDto employeeDto , BindingResult bindingResult) {
        employeeCreateByRequestDtoValidator.validate(employeeDto,bindingResult);
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(bindingResult.getAllErrors(),HttpStatus.OK);
        }
        iEmployeeService.createNewEmployee(employeeDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PutMapping("/editEmployee")
    public ResponseEntity<?> editEmployee(@Valid @RequestBody EmployeeDto employeeDto, BindingResult bindingResult) {
        employeeDto.setId_Employee_Type(1L);
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(bindingResult.getAllErrors(),HttpStatus.NOT_ACCEPTABLE);
        }
        iEmployeeService.editEmployee(employeeDto);

        return new ResponseEntity<>(employeeDto, HttpStatus.OK);
    }
}
