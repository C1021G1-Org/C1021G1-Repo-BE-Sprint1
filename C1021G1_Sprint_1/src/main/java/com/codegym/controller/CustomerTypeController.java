package com.codegym.controller;

import com.codegym.model.CustomerType;
import com.codegym.service.ICustomerTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
public class CustomerTypeController {
    @Autowired
    private ICustomerTypeService iCustomerTypeService;
    String[] customerTypeList = {
            "Titanium",
            "Platinum",
            "Gold",
            "Silver",
            "Normal"
    };




    //     Chạy tool postman dòng này trước kiểm tra database và comment lại.


    @RequestMapping("/customerType/api")
    public ResponseEntity<?> getCreate() {
        for (int i = 0; i < customerTypeList.length; i++) {
            CustomerType customerType = new CustomerType(customerTypeList[i]);
            iCustomerTypeService.save(customerType);
        }
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    /* Lấy danh sách tài nguyên */


    @GetMapping("/customerType-list")
    public ResponseEntity<List<CustomerType>> findAllCustomerType() {
        List<CustomerType> customerTypeList = iCustomerTypeService.findAll();
        if (customerTypeList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(customerTypeList, HttpStatus.OK);
    }
}
