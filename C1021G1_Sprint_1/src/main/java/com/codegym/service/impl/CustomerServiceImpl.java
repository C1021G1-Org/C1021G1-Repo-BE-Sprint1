package com.codegym.service.impl;

import com.codegym.model.Customer;
import com.codegym.repository.ICustomerRepository;
import com.codegym.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private ICustomerRepository customerRepository;

    @Override
    public Page<Customer> findAllCustomer(Pageable pageable) {
        return customerRepository.findAllByCustomer(pageable);
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteCustomerByIdCustomer(id);
    }


    @Override
    public List<Customer> searchCustomer(String keyword) {
        return customerRepository.findByAllField(keyword);
    }
}
