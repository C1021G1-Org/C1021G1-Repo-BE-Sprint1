package com.codegym.service.impl;

import com.codegym.model.Customer;
import com.codegym.repository.ICustomerRepository;
import com.codegym.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    ICustomerRepository customerRepository;

    /*ThangDBX lấy dữ liệu của khách hàng  */
    @Override
    public Customer findCustomerById(Long id) {
        return customerRepository.findCustomerByID(id);
    }

    @Override
    public Customer findByIdPersonal(Long id) {
        return customerRepository.findByIdPersonal(id);
    }

    /*ThangDBX cap nhat liệu của khách hàng  */
    @Override
    public void updatePersonalInfo(Customer customer) {
        String name = customer.getNameCustomer();
        Boolean gender = customer.getGenderCustomer();
        String email = customer.getEmailCustomer();
        String phone = customer.getPhoneCustomer();
        String birth = customer.getBirthdayCustomer();
        String idCard = customer.getIdCardCustomer();
        Long idCountry = customer.getCountries().getId();
        String address = customer.getAddressCustomer();
        String image = customer.getImageCustomer();
        Long id = customer.getId();

        customerRepository.updatePersonalInfo(name,gender,email,phone,birth,idCard,idCountry,address,image,id);

    }


}
