package com.codegym.service;

import com.codegym.model.Countries;
import com.codegym.model.Customer;

public interface ICustomerService {

    /*ThangDBX lấy dữ liệu của khách hàng  */
    Customer findCustomerById(Long id);

    /* lấy dữ liệu của khách hàng  */
    Customer findByIdPersonal(Long id);

    /*ThangDBX: cập nhật dữ liệu của khách hàng  */
    void updatePersonalInfo(Customer customer);


}
