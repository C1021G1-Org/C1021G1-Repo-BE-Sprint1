package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.model.CustomerType;
import com.codegym.repository.ICustomerRepository;
import com.codegym.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    @GetMapping
    public ResponseEntity<Page<Customer>> getAllCustomer(@PageableDefault(value = 5) Pageable pageable) {
        Page<Customer> customers = customerService.findAllCustomer(pageable);
        if (customers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/customerType")
    public ResponseEntity<List<Customer>> getAllCustomerType(){
        List<CustomerType> customerTypes = cu
    }

}
