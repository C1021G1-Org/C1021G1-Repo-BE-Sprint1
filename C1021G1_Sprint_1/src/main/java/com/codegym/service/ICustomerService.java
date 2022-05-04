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

//        List<Customer> searchCustomer(String keyword);

        Customer findById(Long id);

        void save(CustomerDto customerDto);


        void update(CustomerDto customerDto);

    Page<Customer> searchCustomerByEmail(String keyword, Pageable pageable);

    Page<Customer> searchCustomerByName(String keyword, Pageable pageable);

    Page<Customer> searchCustomerByAddress(String keyword, Pageable pageable);

    Page<Customer> searchCustomerByCountry(String keyword, Pageable pageable);

    Page<Customer> searchCustomerByCustomerType(String keyword, Pageable pageable);

    Page<Customer> searchCustomerByPhone(String keyword, Pageable pageable);

    Page<Customer> searchCustomerByIdCrad(String keyword, Pageable pageable);

    List<Customer> getAllCustomerNotPagination();


}
