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

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TicketController_getFirstTicketById {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TicketController ticketController;


    @Test
    public void getFirstTicketById_idFlight_1() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("http://localhost:8080/api/findTicket?idFlight=null&typeSeat=vip&idTicket=7"))
                .andDo(print())
                .andExpect(status().is4xxClientError());


    }
    @Test
    public void getFirstTicketById_idFlight_2() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("http://localhost:8080/api/findTicket?idFlight=&typeSeat=vip&idTicket=7"))
                .andDo(print())
                .andExpect(status().is4xxClientError());


    }

    @Test
    public void getFirstTicketById_idFlight_3() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("http://localhost:8080/api/findTicket?idFlight=2&typeSeat=vip&idTicket=7"))
                .andDo(print())
                .andExpect(status().is4xxClientError());


    }
    @Test
    public void getFirstTicketById_typeSeat_1() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("http://localhost:8080/api/findTicket?idFlight=1&typeSeat=null&idTicket=7"))
                .andDo(print())
                .andExpect(status().is4xxClientError());


    }
    @Test
    public void getFirstTicketById_typeSeat_2() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("http://localhost:8080/api/findTicket?idFlight=1&typeSeat=&idTicket=7"))
                .andDo(print())
                .andExpect(status().is4xxClientError());


    }

    @Test
    public void getFirstTicketById_typeSeat_3() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("http://localhost:8080/api/findTicket?idFlight=1&typeSeat=vippromax&idTicket=7"))
                .andDo(print())
                .andExpect(status().is4xxClientError());


    }
    @Test
    public void getFirstTicketById_idTicket_1() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("http://localhost:8080/api/findTicket?idFlight=1&typeSeat=vip&idTicket=null"))
                .andDo(print())
                .andExpect(status().is4xxClientError());


    }
    @Test
    public void getFirstTicketById_idTicket_2() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("http://localhost:8080/api/findTicket?idFlight=1&typeSeat=vip&idTicket="))
                .andDo(print())
                .andExpect(status().is4xxClientError());


    }
    @Test
    public void getFirstTicketById_idTicket_3() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("http://localhost:8080/api/findTicket?idFlight=1&typeSeat=vip&idTicket=6"))
                .andDo(print())
                .andExpect(status().is4xxClientError());


    }

    @Test
    public void getFirstTicketById_idTicket_4() throws Exception {
        ResponseEntity<Ticket> responseEntity = this.ticketController.getFirstTicketById(1L,"vip",7L);
        Assertions.assertEquals(200, responseEntity.getStatusCodeValue());
        Assertions.assertEquals(7, responseEntity.getBody().getId());
        Assertions.assertEquals(null, responseEntity.getBody().getBuyerTicket());
        Assertions.assertEquals("TK-007", responseEntity.getBody().getCodeTicket());
        Assertions.assertEquals(true, responseEntity.getBody().getDelFlagTicket());
        Assertions.assertEquals(null, responseEntity.getBody().getEmailTicket());
        Assertions.assertEquals(null, responseEntity.getBody().getGenderTicket());
        Assertions.assertEquals(null, responseEntity.getBody().getPhoneTicket());
        Assertions.assertEquals(2, responseEntity.getBody().getPointTicket());
        Assertions.assertEquals(500000, responseEntity.getBody().getPriceTicket());
        Assertions.assertEquals(null, responseEntity.getBody().getStatusTicket());
        Assertions.assertEquals(null, responseEntity.getBody().getCustomer());
        Assertions.assertEquals(null, responseEntity.getBody().getEmployee());
        Assertions.assertEquals(7, responseEntity.getBody().getSeat().getId());

    }


}
