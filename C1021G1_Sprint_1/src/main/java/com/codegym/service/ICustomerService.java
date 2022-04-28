package com.codegym.service;


import com.codegym.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface ICustomerService {
        Page<Customer> findAllCustomer(String name, Pageable pageable);

        void remove(Long id);

        List<Customer> searchCustomer(String keyword);

        Customer findById(Long id);

}
