package com.codegym.controller;

import com.codegym.dto.EmployeeDto;
import com.codegym.dto.EmployeeFindIdDto;
import com.codegym.validation.EmployeeCreateByRequestDtoValidator;
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
import org.springframework.validation.BindingResult;

import javax.validation.Valid;

import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin("*")
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private IEmployeeService iEmployeeService;
  
    @Autowired
    private EmployeeCreateByRequestDtoValidator employeeCreateByRequestDtoValidator;
  
    @Autowired
    private IEmployeeTypeService iEmployeeTypeService;
  
    @GetMapping("/find-id/{id}")
    public ResponseEntity<EmployeeFindIdDto> findById(@PathVariable Long id) {
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
            return new ResponseEntity<>(bindingResult.getAllErrors(),HttpStatus.BAD_REQUEST);
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
  
    @GetMapping("")
    public ResponseEntity<Page<Employee>> getAllEmployee(@RequestParam(defaultValue = "0") int page){
        Page<Employee> employees = iEmployeeService.findAllEmployee(PageRequest.of(page,10));
        if (employees.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/not-pagination")
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
