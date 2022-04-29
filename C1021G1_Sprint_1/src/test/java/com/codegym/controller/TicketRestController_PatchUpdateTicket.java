package com.codegym.controller;

import com.codegym.model.Ticket;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TicketRestController_PatchUpdateTicket {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void update_codeTicket_19() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .patch("/ticket/pay/{code}", "null"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void update_codeTicket_20() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .patch("/ticket/pay/{code}", ""))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void update_codeTicket_21() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .patch("/ticket/pay/{code}", "NVM-1200"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void update_codeTicket_22() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .patch("/ticket/pay/{code}", "NVM-111"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }
}
