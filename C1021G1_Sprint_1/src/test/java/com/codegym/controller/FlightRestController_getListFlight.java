package com.codegym.controller;

import com.codegym.model.Flight;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class FlightRestController_getListFlight {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private FlightController flightController;

    @Test
    public void getListFlight_7_1(){
        ResponseEntity<?> response =
                this.flightController.searchFlight(null,"","","","",Pageable.unpaged());
        Assertions.assertEquals(404, response.getStatusCodeValue());
    }
    @Test
    public void getListFlight_7_2(){
        ResponseEntity<?> response =
                this.flightController.searchFlight("",null,"","","", Pageable.unpaged());
        Assertions.assertEquals(404, response.getStatusCodeValue());
    }
    @Test
    public void getListFlight_7_3(){
        ResponseEntity<?> response =
                this.flightController.searchFlight("","",null,"","", Pageable.unpaged());
        Assertions.assertEquals(404, response.getStatusCodeValue());
    }
    @Test
    public void getListFlight_7_4(){
        ResponseEntity<?> response =
                this.flightController.searchFlight("","","",null,"", Pageable.unpaged());
        Assertions.assertEquals(404, response.getStatusCodeValue());
    }
    @Test
    public void getListFlight_7_5(){
        ResponseEntity<?> response =
                this.flightController.searchFlight("","","","",null, Pageable.unpaged());
        Assertions.assertEquals(404, response.getStatusCodeValue());
    }
    @Test
    public void getListFlight_8_1(){
        ResponseEntity<?> response =
                this.flightController.searchFlight("","Đà Nẵng (DAD)","Hà Nội (HAN)","2022-05-15","2022-05-15", Pageable.unpaged());
        Assertions.assertEquals(404, response.getStatusCodeValue());
    }
    @Test
    public void getListFlight_8_2(){
        ResponseEntity<?> response =
                this.flightController.searchFlight("oneway","","Hà Nội (HAN)","2022-05-15","2022-05-15", Pageable.unpaged());
        Assertions.assertEquals(404, response.getStatusCodeValue());
    }
    @Test
    public void getListFlight_8_3(){
        ResponseEntity<?> response =
                this.flightController.searchFlight("oneway","Hà Nội (HAN)","","2022-05-15","2022-05-15", Pageable.unpaged());
        Assertions.assertEquals(404, response.getStatusCodeValue());
    }
    @Test
    public void getListFlight_8_4(){
        ResponseEntity<?> response =
                this.flightController.searchFlight("oneway","Hà Nội (HAN)","Đà Nẵng (DAD)","","2022-05-15", Pageable.unpaged());
        Assertions.assertEquals(404, response.getStatusCodeValue());
    }
    @Test
    public void getListFlight_8_5(){
        ResponseEntity<?> response =
                this.flightController.searchFlight("oneway","Hà Nội (HAN)","Đà Nẵng (DAD)","2022-05-15","", Pageable.unpaged());
        Assertions.assertEquals(404, response.getStatusCodeValue());
    }
    @Test
    public void getListFlight_9_1() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/search", "oneway"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    @Test
    public void getListFlight_9_2() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/search", "Hà Nội (HAN)"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    @Test
    public void getListFlight_9_3() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/search", "Đà Nẵng (DAD)"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    @Test
    public void getListFlight_9_4() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/search", "2022-05-15"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    @Test
    public void getListFlight_9_5() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/search", "2022-05-15"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
}
