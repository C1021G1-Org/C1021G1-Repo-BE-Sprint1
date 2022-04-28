package com.codegym.controller;

import com.codegym.dto.CustomerPersonalInfoDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerController_updateCustomerPersonalInfo {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

//    @Test
//    public void updateCustomerPersonalInfo_19() throws Exception{
//        CustomerPersonalInfoDto customerDtop = new CustomerPersonalInfoDto();
//
//        customerDtop.setId(1l);
//        customerDtop.setNameCustomer("");
//        customerDtop.
//
//    }
}
