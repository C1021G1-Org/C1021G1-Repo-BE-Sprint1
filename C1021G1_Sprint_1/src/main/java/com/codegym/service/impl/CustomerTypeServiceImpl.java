package com.codegym.service.impl;


import com.codegym.model.CustomerType;
import com.codegym.repository.ICustomerTypeRepository;
import com.codegym.service.ICustomerTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerTypeServiceImpl implements ICustomerTypeService {
    @Autowired
    private ICustomerTypeRepository iCustomerTypeRepository;

    @Override
    public void save(CustomerType customerType) {
        iCustomerTypeRepository.save(customerType);
    }

    @Override
    public List<CustomerType> findAll() {
        return iCustomerTypeRepository.findAll();
    }
}
