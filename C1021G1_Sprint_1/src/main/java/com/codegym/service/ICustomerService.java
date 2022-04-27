package com.codegym.service;

import com.codegym.dto.CustomerDto;
import com.codegym.model.Customer;

import java.util.List;


public interface ICustomerService {
    void save(CustomerDto customerDto);

    List<Customer> findAll();
}
