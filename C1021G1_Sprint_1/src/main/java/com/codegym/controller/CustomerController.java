package com.codegym.controller;


import com.codegym.model.Customer;
import com.codegym.model.CustomerType;
import com.codegym.service.ICustomerService;
import com.codegym.service.ICustomerTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@CrossOrigin("*")
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private ICustomerTypeService iCustomerTypeService;

    @Autowired
    private ICustomerService customerService;

    @GetMapping("/list")
    public ResponseEntity<Page<Customer>> getAllCustomer(@PageableDefault(value = 5) String name, Pageable pageable) {
        Page<Customer> customers = customerService.findAllCustomer(name, pageable);
        if (customers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/customerType")
    public ResponseEntity<List<CustomerType>> getAllCustomerType(){
        List<CustomerType> customerTypes = iCustomerTypeService.findAll();
        if (customerTypes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
            return new ResponseEntity<>(customerTypes, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable Long id) {
        Customer customers = customerService.findById(id);
        if (customers == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        customerService.remove(id);
        return new ResponseEntity<>(customers, HttpStatus.NO_CONTENT);
    }

        @GetMapping("/search")
    public ResponseEntity<List<Customer>> searchCustomer(@RequestParam(defaultValue = "") String keyword){
        List<Customer> customerList = customerService.searchCustomer(keyword);
        if (customerList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
            return new ResponseEntity<>(customerList, HttpStatus.OK);
    }

}
