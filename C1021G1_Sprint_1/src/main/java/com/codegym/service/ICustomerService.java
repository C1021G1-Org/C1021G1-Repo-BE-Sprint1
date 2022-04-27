package com.codegym.service;

import com.codegym.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICustomerService {
        Page<Customer> findAllCustomer(Pageable pageable);

        void deleteCustomer(Long id);

        List<Customer> searchCustomer(String keyword);

}
