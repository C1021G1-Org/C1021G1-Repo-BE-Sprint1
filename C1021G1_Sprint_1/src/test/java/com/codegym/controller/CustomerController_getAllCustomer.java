package com.codegym.controller;

import com.codegym.model.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class CustomerController_getAllCustomer {

    @Autowired
    private CustomerController customerController;
//*Long LT* Test
//    @Test
//    public void getAllCustomer_5() {
//        ResponseEntity<Page<Customer>> responseEntity
//                = this.customerController.getAllCustomer(PageRequest.of(0, 10));
//
//        Assertions.assertEquals(404, responseEntity.getStatusCodeValue());
//    }
//    @Test
//    public  void getAllCustomer_6() {
//        ResponseEntity<Page<Customer>> responseEntity
//                = this.customerController.getAllCustomer(PageRequest.of(0, 10));
//        Assertions.assertEquals(200, responseEntity.getStatusCodeValue());
//        Assertions.assertEquals(2, responseEntity.getBody().getTotalPages());
//        Assertions.assertEquals(15, responseEntity.getBody().getTotalElements());
//        Assertions.assertEquals("Phạm Duy Thuận", responseEntity.getBody().getContent().get(9).getNameCustomer());
//        Assertions.assertEquals("Quảng Trị", responseEntity.getBody().getContent().get(9).getAddressCustomer());
//        Assertions.assertEquals("1997-04-03", responseEntity.getBody().getContent().get(9).getBirthdayCustomer());
//    }

}
