package com.codegym.service.impl;




import com.codegym.dto.CustomerDto;

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

    private ICustomerRepository iCustomerRepository;


    @Override
    public Page<Customer> findAllCustomer(Pageable pageable) {
        return iCustomerRepository.findAllByCustomer(pageable);
    }

    @Override
    public void remove(Long id) {
        iCustomerRepository.deleteCustomerByIdCustomer(id);
    }


    @Override
    public List<Customer> searchCustomer(String keyword) {
        return iCustomerRepository.searchAllByFields(keyword);
    }

    @Override
    public Customer findById(Long id) {
        return iCustomerRepository.findByIdCustomer(id);
    }


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
    public void update(CustomerDto customerDto) {
        iCustomerRepository.updateCustomer(
                customerDto.getNameCustomer(),
                customerDto.getPhoneCustomer(),
                customerDto.getGenderCustomer(),
                customerDto.getEmailCustomer(),
                customerDto.getIdCardCustomer(),
                customerDto.getBirthdayCustomer(),
                customerDto.getAddressCustomer(),
                customerDto.getCustomerType(),
                customerDto.getCountries(),
                false,
                customerDto.getId());
    }


}
