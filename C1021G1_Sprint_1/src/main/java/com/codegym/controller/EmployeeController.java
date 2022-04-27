package com.codegym.controller;

import com.codegym.dto.EmployeeDto;
import com.codegym.model.Employee;
import com.codegym.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController {
    @Autowired
    private IEmployeeService iEmployeeService;
    @GetMapping("employee")
    public ResponseEntity<Page<Employee>> findAllCustomer(Pageable pageable){
        Page<Employee> employeeList = iEmployeeService.findAllEmployee(pageable);
        if (employeeList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(employeeList, HttpStatus.OK);
    }
    @GetMapping("employee/{id}")
    public ResponseEntity<Employee> findCustomerById(@PathVariable Long id) {
        Employee employee = iEmployeeService.findEmployeeById(id);
        if (employee == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }
    @PostMapping("/employee")
    public ResponseEntity<?> saveEmployee(@RequestBody EmployeeDto employee) {
        iEmployeeService.save(employee);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("employee")
    public ResponseEntity<Employee> updateEmployee( @RequestBody EmployeeDto employeeDto) {
        iEmployeeService.save(employeeDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
