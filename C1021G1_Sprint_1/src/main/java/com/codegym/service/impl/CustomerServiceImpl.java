package com.codegym.service.impl;

import com.codegym.dto.CustomerDto;
import com.codegym.model.Customer;
import com.codegym.repository.ICustomerRepository;
import com.codegym.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class CustomerServiceImpl implements ICustomerService {
    @Autowired
    private ICustomerRepository iCustomerRepository;

    @Override
    public void save(CustomerDto customerDto) {

        iCustomerRepository.saveCustomer(
                customerDto.getNameCustomer(),
                customerDto.getPhoneCustomer(),
                customerDto.getGenderCustomer(),
                customerDto.getEmailCustomer(),
                customerDto.getIdCardCustomer(),
                customerDto.getBirthdayCustomer(),
                customerDto.getAddressCustomer(),
                customerDto.getCustomerType(),
                customerDto.getCountries(),
                false);
    }

    @Override
    public List<Customer> findAll() {
        return iCustomerRepository.findAll();
    }
}
