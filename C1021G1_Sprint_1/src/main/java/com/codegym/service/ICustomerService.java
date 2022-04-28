package com.codegym.service;



import com.codegym.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.codegym.dto.CustomerDto;
import com.codegym.model.Customer;


import java.util.List;


public interface ICustomerService {

        Page<Customer> findAllCustomer(Pageable pageable);

        void remove(Long id);

        List<Customer> searchCustomer(String keyword);

        Customer findById(Long id);

        void save(CustomerDto customerDto);


        void update(CustomerDto customerDto);
}
