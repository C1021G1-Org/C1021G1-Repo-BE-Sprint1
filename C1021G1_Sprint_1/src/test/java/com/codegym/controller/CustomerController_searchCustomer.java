package com.codegym.controller;

import com.codegym.model.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerController_searchCustomer {

    @Autowired
    private CustomerController customerController;

    @Autowired
    private MockMvc mockMvc;

//    test tìm kiếm trường hợp null. Cái này e test cho cả tất cả các trường.
//    đường dẫn này tìm kiếm cho tất cả các trường.
    @Test
    public void searchCustomer_1() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("http://localhost:8080/customer/search?keyword=null "))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

//    test tìm kiếm trường hợp rỗng''.
    @Test
    public void searchCustomer_2() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("http://localhost:8080/customer/search?keyword=''"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
//    test tìm kiếm trường hợp không tồn tại trong database
    @Test
    public void searchCustomer_3() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("http://localhost:8080/customer/search?keyword=longlt"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    @Test
    public void searchCustomer_4() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("http://localhost:8080/customer/search?keyword=Nguyên"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

}
