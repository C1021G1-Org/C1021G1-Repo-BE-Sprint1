package com.codegym.controller;


import com.codegym.model.Ticket;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class TicketController_getListTicketByIdFlight {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TicketController ticketController;

    @Test
    public void getListTicketByIdFlight_idFlight_1() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("http://localhost:8080/api/listTicketType?id=null&typeSeat=vip"))
                .andDo(print())
                .andExpect(status().is4xxClientError());


    }

    @Test
    public void getListTicketByIdFlight_idFlight_2() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("http://localhost:8080/api/listTicketType?id=&typeSeat=vip"))
                .andDo(print())
                .andExpect(status().is4xxClientError());


    }

    @Test
    public void getListTicketByIdFlight_idFlight_3() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("http://localhost:8080/api/listTicketType?id=3&typeSeat=vip"))
                .andDo(print())
                .andExpect(status().is4xxClientError());


    }

    @Test
    public void getListTicketByIdFlight_tyPeSeat_1() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("http://localhost:8080/api/listTicketType?id=1&typeSeat=null"))
                .andDo(print())
                .andExpect(status().is4xxClientError());


    }

    @Test
    public void getListTicketByIdFlight_tyPeSeat_2() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("http://localhost:8080/api/listTicketType?id=1&typeSeat="))
                .andDo(print())
                .andExpect(status().is4xxClientError());


    }

    @Test
    public void getListTicketByIdFlight_tyPeSeat_3() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("http://localhost:8080/api/listTicketType?id=1&typeSeat=vippromax"))
                .andDo(print())
                .andExpect(status().is4xxClientError());


    }

    @Test
    public void getListTicketByIdFlight_6() {
        ResponseEntity<List<Ticket>> responseEntity = this.ticketController.getListTicketByIdFlight(1L, "vip");
        Assertions.assertEquals(200, responseEntity.getStatusCodeValue());
        Assertions.assertEquals(8, responseEntity.getBody().get(1).getId());
        Assertions.assertEquals(8, responseEntity.getBody().get(1).getSeat().getId());

    }

}
