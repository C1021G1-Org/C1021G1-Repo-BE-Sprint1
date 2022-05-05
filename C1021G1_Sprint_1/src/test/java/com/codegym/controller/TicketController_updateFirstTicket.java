package com.codegym.controller;


import com.codegym.dto.EmployeeTicketDto;
import com.codegym.dto.TicketFirstDto;
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
public class TicketController_updateFirstTicket {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void updateFirstTicket_buyerTicket_20() throws Exception {
        TicketFirstDto ticketFirstDto = new TicketFirstDto();


        ticketFirstDto.setBuyerTicket("");
        ticketFirstDto.setBirthdayTicket("1995-01-01");
        ticketFirstDto.setEmailTicket("hdqphu1301@gmail.com");
        ticketFirstDto.setGenderTicket("1");
        ticketFirstDto.setPriceTicket(2000000.0);
        ticketFirstDto.setPhoneTicket("0909123123");
        ticketFirstDto.setIdCard("123456789");
        ticketFirstDto.setCustomer(null);
        ticketFirstDto.setEmployee(1L);


        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("http://localhost:8080/api/firstUpdate?idFlight=1&typeSeat=thương gia")
                        .content(this.objectMapper.writeValueAsString(ticketFirstDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void updateFirstTicket_buyerTicket_19() throws Exception {
        TicketFirstDto ticketFirstDto = new TicketFirstDto();


        ticketFirstDto.setBuyerTicket(null);
        ticketFirstDto.setBirthdayTicket("1995-01-01");
        ticketFirstDto.setEmailTicket("hdqphu1301@gmail.com");
        ticketFirstDto.setGenderTicket("1");
        ticketFirstDto.setPriceTicket(2000000.0);
        ticketFirstDto.setPhoneTicket("0909123123");
        ticketFirstDto.setIdCard("123456789");
        ticketFirstDto.setCustomer(null);
        ticketFirstDto.setEmployee(1L);


        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("http://localhost:8080/api/firstUpdate?idFlight=1&typeSeat=thương gia")
                        .content(this.objectMapper.writeValueAsString(ticketFirstDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    //
    @Test
    public void updateFirstTicket_buyerTicket_21() throws Exception {
        TicketFirstDto ticketFirstDto = new TicketFirstDto();


        ticketFirstDto.setBuyerTicket("thanh tam 123");
        ticketFirstDto.setBirthdayTicket("1995-01-01");
        ticketFirstDto.setEmailTicket("hdqphu1301@gmail.com");
        ticketFirstDto.setGenderTicket("1");
        ticketFirstDto.setPriceTicket(2000000.0);
        ticketFirstDto.setPhoneTicket("0909123123");
        ticketFirstDto.setIdCard("123456789");
        ticketFirstDto.setCustomer(null);
        ticketFirstDto.setEmployee(1L);


        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("http://localhost:8080/api/firstUpdate?idFlight=1&typeSeat=thương gia")
                        .content(this.objectMapper.writeValueAsString(ticketFirstDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }//

    @Test
    public void updateFirstTicket_birthdayTicket_19() throws Exception {
        TicketFirstDto ticketFirstDto = new TicketFirstDto();
        ticketFirstDto.setBuyerTicket("thanh tam");
        ticketFirstDto.setBirthdayTicket(null);
        ticketFirstDto.setEmailTicket("hdqphu1301@gmail.com");
        ticketFirstDto.setGenderTicket("1");
        ticketFirstDto.setPriceTicket(2000000.0);
        ticketFirstDto.setPhoneTicket("0909123123");
        ticketFirstDto.setIdCard("123456789");
        ticketFirstDto.setCustomer(null);
        ticketFirstDto.setEmployee(1L);


        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("http://localhost:8080/api/firstUpdate?idFlight=1&typeSeat=thương gia")
                        .content(this.objectMapper.writeValueAsString(ticketFirstDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void updateFirstTicket_birthdayTicket_20() throws Exception {
        TicketFirstDto ticketFirstDto = new TicketFirstDto();
        ticketFirstDto.setBuyerTicket("thanh tam");
        ticketFirstDto.setBirthdayTicket("");
        ticketFirstDto.setEmailTicket("hdqphu1301@gmail.com");
        ticketFirstDto.setGenderTicket("1");
        ticketFirstDto.setPriceTicket(2000000.0);
        ticketFirstDto.setPhoneTicket("0909123123");
        ticketFirstDto.setIdCard("123456789");
        ticketFirstDto.setCustomer(null);
        ticketFirstDto.setEmployee(1L);


        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("http://localhost:8080/api/firstUpdate?idFlight=1&typeSeat=thương gia")
                        .content(this.objectMapper.writeValueAsString(ticketFirstDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    //
    @Test
    public void updateFirstTicket_birthdayTicket_21() throws Exception {
        TicketFirstDto ticketFirstDto = new TicketFirstDto();
        ticketFirstDto.setBuyerTicket("thanh tam");
        ticketFirstDto.setBirthdayTicket("1995-01-01 17:05");
        ticketFirstDto.setEmailTicket("hdqphu1301@gmail.com");
        ticketFirstDto.setGenderTicket("1");
        ticketFirstDto.setPriceTicket(2000000.0);
        ticketFirstDto.setPhoneTicket("0909123123");
        ticketFirstDto.setIdCard("123456789");
        ticketFirstDto.setCustomer(null);
        ticketFirstDto.setEmployee(1L);


        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("http://localhost:8080/api/firstUpdate?idFlight=1&typeSeat=thương gia")
                        .content(this.objectMapper.writeValueAsString(ticketFirstDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    //
    @Test
    public void updateFirstTicket_emailTicket_19() throws Exception {
        TicketFirstDto ticketFirstDto = new TicketFirstDto();
        ticketFirstDto.setBuyerTicket("thanh tam");
        ticketFirstDto.setBirthdayTicket("1995-01-01");
        ticketFirstDto.setEmailTicket(null);
        ticketFirstDto.setGenderTicket("1");
        ticketFirstDto.setPriceTicket(2000000.0);
        ticketFirstDto.setPhoneTicket("0909123123");
        ticketFirstDto.setIdCard("123456789");
        ticketFirstDto.setCustomer(null);
        ticketFirstDto.setEmployee(1L);


        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("http://localhost:8080/api/firstUpdate?idFlight=1&typeSeat=thương gia")
                        .content(this.objectMapper.writeValueAsString(ticketFirstDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    //
    @Test
    public void updateFirstTicket_emailTicket_20() throws Exception {
        TicketFirstDto ticketFirstDto = new TicketFirstDto();
        ticketFirstDto.setBuyerTicket("thanh tam");
        ticketFirstDto.setBirthdayTicket("1995-01-01");
        ticketFirstDto.setEmailTicket("");
        ticketFirstDto.setGenderTicket("1");
        ticketFirstDto.setPriceTicket(2000000.0);
        ticketFirstDto.setPhoneTicket("0909123123");
        ticketFirstDto.setIdCard("123456789");
        ticketFirstDto.setCustomer(null);
        ticketFirstDto.setEmployee(1L);


        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("http://localhost:8080/api/firstUpdate?idFlight=1&typeSeat=thương gia")
                        .content(this.objectMapper.writeValueAsString(ticketFirstDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    //
    @Test
    public void updateFirstTicket_emailTicket_21() throws Exception {
        TicketFirstDto ticketFirstDto = new TicketFirstDto();
        ticketFirstDto.setBuyerTicket("thanh tam");
        ticketFirstDto.setBirthdayTicket("1995-01-01");
        ticketFirstDto.setEmailTicket("quocphu.com");
        ticketFirstDto.setGenderTicket("1");
        ticketFirstDto.setPriceTicket(2000000.0);
        ticketFirstDto.setPhoneTicket("0909123123");
        ticketFirstDto.setIdCard("123456789");
        ticketFirstDto.setCustomer(null);
        ticketFirstDto.setEmployee(1L);


        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("http://localhost:8080/api/firstUpdate?idFlight=1&typeSeat=thương gia")
                        .content(this.objectMapper.writeValueAsString(ticketFirstDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    @Test
    public void updateFirstTicket_phoneTicket_19() throws Exception {
        TicketFirstDto ticketFirstDto = new TicketFirstDto();
        ticketFirstDto.setBuyerTicket("thanh tam");
        ticketFirstDto.setBirthdayTicket("1995-01-01");
        ticketFirstDto.setEmailTicket("quocphu@gmail.com");
        ticketFirstDto.setGenderTicket("1");
        ticketFirstDto.setPriceTicket(2000000.0);
        ticketFirstDto.setPhoneTicket(null);
        ticketFirstDto.setIdCard("123456789");
        ticketFirstDto.setCustomer(null);
        ticketFirstDto.setEmployee(1L);


        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("http://localhost:8080/api/firstUpdate?idFlight=1&typeSeat=thương gia")
                        .content(this.objectMapper.writeValueAsString(ticketFirstDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    @Test
    public void updateFirstTicket_phoneTicket_20() throws Exception {
        TicketFirstDto ticketFirstDto = new TicketFirstDto();
        ticketFirstDto.setBuyerTicket("thanh tam");
        ticketFirstDto.setBirthdayTicket("1995-01-01");
        ticketFirstDto.setEmailTicket("quocphu@gmail.com");
        ticketFirstDto.setGenderTicket("1");
        ticketFirstDto.setPriceTicket(2000000.0);
        ticketFirstDto.setPhoneTicket("");
        ticketFirstDto.setIdCard("123456789");
        ticketFirstDto.setCustomer(null);
        ticketFirstDto.setEmployee(1L);


        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("http://localhost:8080/api/firstUpdate?idFlight=1&typeSeat=thương gia")
                        .content(this.objectMapper.writeValueAsString(ticketFirstDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    @Test
    public void updateFirstTicket_phoneTicket_21() throws Exception {
        TicketFirstDto ticketFirstDto = new TicketFirstDto();
        ticketFirstDto.setBuyerTicket("thanh tam");
        ticketFirstDto.setBirthdayTicket("1995-01-01");
        ticketFirstDto.setEmailTicket("quocphu@gmail.com");
        ticketFirstDto.setGenderTicket("1");
        ticketFirstDto.setPriceTicket(2000000.0);
        ticketFirstDto.setPhoneTicket("464989564654564654");
        ticketFirstDto.setIdCard("123456789");
        ticketFirstDto.setCustomer(null);
        ticketFirstDto.setEmployee(1L);


        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("http://localhost:8080/api/firstUpdate?idFlight=1&typeSeat=thương gia")
                        .content(this.objectMapper.writeValueAsString(ticketFirstDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    @Test
    public void updateFirstTicket_priceTicket_19() throws Exception {
        TicketFirstDto ticketFirstDto = new TicketFirstDto();
        ticketFirstDto.setBuyerTicket("thanh tam");
        ticketFirstDto.setBirthdayTicket("1995-01-01");
        ticketFirstDto.setEmailTicket("quocphu@gmail.com");
        ticketFirstDto.setGenderTicket("1");
        ticketFirstDto.setPriceTicket(null);
        ticketFirstDto.setPhoneTicket("464989564654564654");
        ticketFirstDto.setIdCard("123456789");
        ticketFirstDto.setCustomer(null);
        ticketFirstDto.setEmployee(1L);


        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("http://localhost:8080/api/firstUpdate?idFlight=1&typeSeat=thương gia")
                        .content(this.objectMapper.writeValueAsString(ticketFirstDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }




//
    @Test
    public void updateFirstTicket_all_24() throws Exception {
        TicketFirstDto ticketFirstDto = new TicketFirstDto();
        ticketFirstDto.setId(18L);
        ticketFirstDto.setBuyerTicket("thanh tam");
        ticketFirstDto.setBirthdayTicket("1995-01-01");
        ticketFirstDto.setEmailTicket("quocphu@gmail.com");
        ticketFirstDto.setGenderTicket("1");
        ticketFirstDto.setPriceTicket(2000000.0);
        ticketFirstDto.setPhoneTicket("0796298932");
        ticketFirstDto.setIdCard("123456789");
        ticketFirstDto.setCustomer(null);
        ticketFirstDto.setEmployee(1L);


        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("http://localhost:8080/api/firstUpdate?idFlight=1&typeSeat=thương gia")
                        .content(this.objectMapper.writeValueAsString(ticketFirstDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }
}
