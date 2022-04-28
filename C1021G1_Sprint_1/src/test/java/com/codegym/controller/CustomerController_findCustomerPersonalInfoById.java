package com.codegym.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerController_findCustomerPersonalInfoById {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CustomerController customerController;

    @Test
    public void findCustomerPersonalInfoById_1() throws Exception{
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/customer/{id}","null"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void findCustomerPersonalInfoById_3() throws Exception{
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/customer/{id}","15"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void findCustomerPersonalInfoById_4() throws Exception{
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/customer/{id}","1"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.address_customer").value("Đà Nẵng"))
                .andExpect(jsonPath("$.birthday_customer").value("1990-12-04"))
                .andExpect(jsonPath("$.del_flag_customer").value(1))
                .andExpect(jsonPath("$.email_customer").value("Nguyen1@gmail.com"))
                .andExpect(jsonPath("$.gender_customer").value(1))
                .andExpect(jsonPath("$.id_card_customer").value("1311221333"))
                .andExpect(jsonPath("$.image_customer").value("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSa4FYDcExqiB-NZf6KGOsoYg2L8rUhQVVO0w&usqp=CAU"))
                .andExpect(jsonPath("$.name_customer").value("Nguyễn Văn Nguyên"))
                .andExpect(jsonPath("$.phone_customer").value("0903111222"))
                .andExpect(jsonPath("$.point_customer").value(100))
                .andExpect(jsonPath("$.id_country.id").value(241))
                .andExpect(jsonPath("$.id_customer_type.id").value(4))
        ;
    }



}
