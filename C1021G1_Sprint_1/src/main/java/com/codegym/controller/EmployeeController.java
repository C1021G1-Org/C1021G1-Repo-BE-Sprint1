package com.codegym.controller;

import com.codegym.model.Employee;
import com.codegym.model.EmployeeType;
import com.codegym.service.IEmployeeService;
import com.codegym.service.IEmployeeTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private IEmployeeService iEmployeeService;

    @Autowired
    private IEmployeeTypeService iEmployeeTypeService;

    @GetMapping("")
    public ResponseEntity<Page<Employee>> getAllEmployee(@RequestParam(defaultValue = "0") int page){
        Page<Employee> employees = iEmployeeService.findAllEmployee(PageRequest.of(page,10));
        if (employees.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<Page<Employee>> getAllEmployeeNotPagination(){
        Page<Employee> employees = iEmployeeService.findAllEmployee(Pageable.unpaged());
        if (employees.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> findById(@PathVariable Long id) {
        Optional<Employee> employee = iEmployeeService.findEmployeeById(id);
        return employee.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/employeeType")
    public ResponseEntity<List<EmployeeType>> getAllEmployeeType() {
        List<EmployeeType> employeeTypes = iEmployeeTypeService.findAllEmployeeType();
        if (employeeTypes.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(employeeTypes, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable Long id) {
        Optional<Employee> employee = iEmployeeService.findEmployeeById(id);
        if (!employee.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        iEmployeeService.deleteEmployee(id);
        return new ResponseEntity<>(employee.get(), HttpStatus.NO_CONTENT);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Employee>> findEmployee(String name, String code, String email, @RequestParam(defaultValue = "0") int page) {
        Page<Employee> employees = iEmployeeService.findEmployee(name, code, email, PageRequest.of(page,10));
        System.out.println(employees.get());
        if (employees.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
}
