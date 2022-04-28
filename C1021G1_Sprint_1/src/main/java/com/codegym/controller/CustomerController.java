package com.codegym.controller;

import com.codegym.dto.CustomerPersonalInfoDto;
import com.codegym.model.Customer;
import com.codegym.service.impl.CustomerServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("")
public class CustomerController {

    @Autowired
    CustomerServiceImpl customerService;

    /*ThangDBX lấy thông tin cá nhân của khách hàng */
    @GetMapping("customer/{id}")
    public ResponseEntity<Customer> findCustomerPersonalInfoById(@PathVariable("id") Long id){
        Customer customer = customerService.findCustomerById(id);
        if (customer != null){
            return new ResponseEntity<>(customer,HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

//    /* ThangDBX cập nhật thông tin bản thân khách hàng  */
//    @GetMapping("customer/edit/{id}")
//    public ResponseEntity<Customer> getCustomerPersonal(@PathVariable("id") Long id){
//        Customer customer = customerService.findCustomerById(id);
//
//        if (customer != null){
//            CustomerPersonalInfoDto customerDto = new CustomerPersonalInfoDto();
//            BeanUtils.copyProperties(customer,customerDto);
//            return new ResponseEntity<>(HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>( customer ,HttpStatus.NOT_FOUND);
//        }
//
//    }

    /* ThangDBX cập nhật thông tin bản thân khách hàng  */
    @PatchMapping("customer/update")
    public ResponseEntity<?> updateCustomerPersonalInfo(@Validated
                                                        @RequestBody CustomerPersonalInfoDto customerDto,
                                           BindingResult bindingResult){
//        new CustomerPersonalInfoDto().validate(customerDto,bindingResult);

        if (bindingResult.hasErrors()){
            return new ResponseEntity<>( bindingResult.getFieldError() ,HttpStatus.NOT_FOUND);
        } else {
            Customer customer = new Customer();
            BeanUtils.copyProperties(customerDto,customer);
            customerService.updatePersonalInfo(customer);
            return new ResponseEntity<>(HttpStatus.OK);
        }

    }
}
