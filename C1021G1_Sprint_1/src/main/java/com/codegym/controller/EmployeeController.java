package com.codegym.controller;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

<<<<<<< HEAD
//    @Autowired
//    private IEmployeeService iEmployeeService;
//
//    @Autowired
//    private IEmployeeTypeService iEmployeeTypeService;
//
//    @GetMapping("")
//    public ResponseEntity<Page<Employee>> getAllEmployee(@RequestParam(defaultValue = "0") int page){
//        Page<Employee> employees = iEmployeeService.findAllEmployee(PageRequest.of(page,10));
//        if (employees.isEmpty()){
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>(employees, HttpStatus.OK);
//    }
//
//    @GetMapping("/employeeType")
//    public ResponseEntity<List<EmployeeType>> getAllEmployeeType() {
//        List<EmployeeType> employeeTypes = iEmployeeTypeService.findAllEmployeeType();
//        if (employeeTypes.isEmpty())
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        return new ResponseEntity<>(employeeTypes, HttpStatus.OK);
//    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<Employee> deleteEmployee(@PathVariable Long id) {
//        Optional<Employee> employee = iEmployeeService.findEmployeeById(id);
//        if (employee.isPresent())
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        iEmployeeService.deleteEmployee(id);
//        return new ResponseEntity<>(employee.get(), HttpStatus.NO_CONTENT);
//    }
=======
>>>>>>> origin
}
