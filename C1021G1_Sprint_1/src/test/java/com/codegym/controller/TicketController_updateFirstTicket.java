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
        EmployeeTicketDto employeeTicketDto = new EmployeeTicketDto();
        employeeTicketDto.setId(1L);
        ticketFirstDto.setBuyerTicket("");
        ticketFirstDto.setBirthdayTicket("1995-01-01");
        ticketFirstDto.setEmailTicket("hdqphu1301@gmail.com");
        ticketFirstDto.setGenderTicket(true);
        ticketFirstDto.setPhoneTicket("0909123123");
        ticketFirstDto.setEmployeeTicketDto(employeeTicketDto);


        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("http://localhost:8080/api/firstUpdate?idFlight=1&typeSeat=vip")
                        .content(this.objectMapper.writeValueAsString(ticketFirstDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void updateFirstTicket_buyerTicket_19() throws Exception {
        TicketFirstDto ticketFirstDto = new TicketFirstDto();
        EmployeeTicketDto employeeTicketDto = new EmployeeTicketDto();
        employeeTicketDto.setId(1L);
        ticketFirstDto.setBuyerTicket(null);
        ticketFirstDto.setBirthdayTicket("1995-01-01");
        ticketFirstDto.setEmailTicket("hdqphu1301@gmail.com");
        ticketFirstDto.setGenderTicket(true);
        ticketFirstDto.setPhoneTicket("0909123123");
        ticketFirstDto.setEmployeeTicketDto(employeeTicketDto);


        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("http://localhost:8080/api/firstUpdate?idFlight=1&typeSeat=vip")
                        .content(this.objectMapper.writeValueAsString(ticketFirstDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void updateFirstTicket_buyerTicket_21() throws Exception {
        TicketFirstDto ticketFirstDto = new TicketFirstDto();
        EmployeeTicketDto employeeTicketDto = new EmployeeTicketDto();
        employeeTicketDto.setId(1L);
        ticketFirstDto.setBuyerTicket("thanh tam 12321");
        ticketFirstDto.setBirthdayTicket("1995-01-01");
        ticketFirstDto.setEmailTicket("hdqphu1301@gmail.com");
        ticketFirstDto.setGenderTicket(true);
        ticketFirstDto.setPhoneTicket("0909123123");
        ticketFirstDto.setEmployeeTicketDto(employeeTicketDto);


        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("http://localhost:8080/api/firstUpdate?idFlight=1&typeSeat=vip")
                        .content(this.objectMapper.writeValueAsString(ticketFirstDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void updateFirstTicket_birthdayTicket_19() throws Exception {
        TicketFirstDto ticketFirstDto = new TicketFirstDto();
        EmployeeTicketDto employeeTicketDto = new EmployeeTicketDto();
        employeeTicketDto.setId(1L);
        ticketFirstDto.setBuyerTicket("thanh tam");
        ticketFirstDto.setBirthdayTicket(null);
        ticketFirstDto.setEmailTicket("hdqphu1301@gmail.com");
        ticketFirstDto.setGenderTicket(true);
        ticketFirstDto.setPhoneTicket("0909123123");
        ticketFirstDto.setEmployeeTicketDto(employeeTicketDto);


        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("http://localhost:8080/api/firstUpdate?idFlight=1&typeSeat=vip")
                        .content(this.objectMapper.writeValueAsString(ticketFirstDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    @Test
    public void updateFirstTicket_birthdayTicket_20() throws Exception {
        TicketFirstDto ticketFirstDto = new TicketFirstDto();
        EmployeeTicketDto employeeTicketDto = new EmployeeTicketDto();
        employeeTicketDto.setId(1L);
        ticketFirstDto.setBuyerTicket("thanh tam");
        ticketFirstDto.setBirthdayTicket("");
        ticketFirstDto.setEmailTicket("hdqphu1301@gmail.com");
        ticketFirstDto.setGenderTicket(true);
        ticketFirstDto.setPhoneTicket("0909123123");
        ticketFirstDto.setEmployeeTicketDto(employeeTicketDto);


        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("http://localhost:8080/api/firstUpdate?idFlight=1&typeSeat=vip")
                        .content(this.objectMapper.writeValueAsString(ticketFirstDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void updateFirstTicket_birthdayTicket_21() throws Exception {
        TicketFirstDto ticketFirstDto = new TicketFirstDto();
        EmployeeTicketDto employeeTicketDto = new EmployeeTicketDto();
        employeeTicketDto.setId(1L);
        ticketFirstDto.setBuyerTicket("thanh tam");
        ticketFirstDto.setBirthdayTicket("1995-01-01 17:05");
        ticketFirstDto.setEmailTicket("hdqphu1301@gmail.com");
        ticketFirstDto.setGenderTicket(true);
        ticketFirstDto.setPhoneTicket("0909123123");
        ticketFirstDto.setEmployeeTicketDto(employeeTicketDto);


        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("http://localhost:8080/api/firstUpdate?idFlight=1&typeSeat=vip")
                        .content(this.objectMapper.writeValueAsString(ticketFirstDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void updateFirstTicket_emailTicket_19() throws Exception {
        TicketFirstDto ticketFirstDto = new TicketFirstDto();
        EmployeeTicketDto employeeTicketDto = new EmployeeTicketDto();
        employeeTicketDto.setId(1L);
        ticketFirstDto.setBuyerTicket("thanh tam");
        ticketFirstDto.setBirthdayTicket("1995-01-01 17:05");
        ticketFirstDto.setEmailTicket(null);
        ticketFirstDto.setGenderTicket(true);
        ticketFirstDto.setPhoneTicket("0909123123");
        ticketFirstDto.setEmployeeTicketDto(employeeTicketDto);


        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("http://localhost:8080/api/firstUpdate?idFlight=1&typeSeat=vip")
                        .content(this.objectMapper.writeValueAsString(ticketFirstDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void updateFirstTicket_emailTicket_20() throws Exception {
        TicketFirstDto ticketFirstDto = new TicketFirstDto();
        EmployeeTicketDto employeeTicketDto = new EmployeeTicketDto();
        employeeTicketDto.setId(1L);
        ticketFirstDto.setBuyerTicket("thanh tam");
        ticketFirstDto.setBirthdayTicket("1995-01-01 17:05");
        ticketFirstDto.setEmailTicket("");
        ticketFirstDto.setGenderTicket(true);
        ticketFirstDto.setPhoneTicket("0909123123");
        ticketFirstDto.setEmployeeTicketDto(employeeTicketDto);


        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("http://localhost:8080/api/firstUpdate?idFlight=1&typeSeat=vip")
                        .content(this.objectMapper.writeValueAsString(ticketFirstDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void updateFirstTicket_emailTicket_21() throws Exception {
        TicketFirstDto ticketFirstDto = new TicketFirstDto();
        EmployeeTicketDto employeeTicketDto = new EmployeeTicketDto();
        employeeTicketDto.setId(1L);
        ticketFirstDto.setBuyerTicket("thanh tam");
        ticketFirstDto.setBirthdayTicket("1995-01-01 17:05");
        ticketFirstDto.setEmailTicket("thanhtamemail.com");
        ticketFirstDto.setGenderTicket(true);
        ticketFirstDto.setPhoneTicket("0909123123");
        ticketFirstDto.setEmployeeTicketDto(employeeTicketDto);


        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("http://localhost:8080/api/firstUpdate?idFlight=1&typeSeat=vip")
                        .content(this.objectMapper.writeValueAsString(ticketFirstDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    @Test
    public void updateFirstTicket_phoneTicket_19() throws Exception {
        TicketFirstDto ticketFirstDto = new TicketFirstDto();
        EmployeeTicketDto employeeTicketDto = new EmployeeTicketDto();
        employeeTicketDto.setId(1L);
        ticketFirstDto.setBuyerTicket("thanh tam");
        ticketFirstDto.setBirthdayTicket("1995-01-01 17:05");
        ticketFirstDto.setEmailTicket("thanhtam@email.com");
        ticketFirstDto.setGenderTicket(true);
        ticketFirstDto.setPhoneTicket(null);
        ticketFirstDto.setEmployeeTicketDto(employeeTicketDto);


        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("http://localhost:8080/api/firstUpdate?idFlight=1&typeSeat=vip")
                        .content(this.objectMapper.writeValueAsString(ticketFirstDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    @Test
    public void updateFirstTicket_phoneTicket_20() throws Exception {
        TicketFirstDto ticketFirstDto = new TicketFirstDto();
        EmployeeTicketDto employeeTicketDto = new EmployeeTicketDto();
        employeeTicketDto.setId(1L);
        ticketFirstDto.setBuyerTicket("thanh tam");
        ticketFirstDto.setBirthdayTicket("1995-01-01 17:05");
        ticketFirstDto.setEmailTicket("thanhtam@email.com");
        ticketFirstDto.setGenderTicket(true);
        ticketFirstDto.setPhoneTicket("");
        ticketFirstDto.setEmployeeTicketDto(employeeTicketDto);


        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("http://localhost:8080/api/firstUpdate?idFlight=1&typeSeat=vip")
                        .content(this.objectMapper.writeValueAsString(ticketFirstDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    @Test
    public void updateFirstTicket_phoneTicket_21() throws Exception {
        TicketFirstDto ticketFirstDto = new TicketFirstDto();
        EmployeeTicketDto employeeTicketDto = new EmployeeTicketDto();
        employeeTicketDto.setId(1L);
        ticketFirstDto.setBuyerTicket("thanh tam");
        ticketFirstDto.setBirthdayTicket("1995-01-01 17:05");
        ticketFirstDto.setEmailTicket("thanhtam@email.com");
        ticketFirstDto.setGenderTicket(true);
        ticketFirstDto.setPhoneTicket("32156156165156164");
        ticketFirstDto.setEmployeeTicketDto(employeeTicketDto);


        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("http://localhost:8080/api/firstUpdate?idFlight=1&typeSeat=vip")
                        .content(this.objectMapper.writeValueAsString(ticketFirstDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void updateFirstTicket_employeeTicketDto_19() throws Exception {
        TicketFirstDto ticketFirstDto = new TicketFirstDto();
        EmployeeTicketDto employeeTicketDto = new EmployeeTicketDto();
        employeeTicketDto.setId(1L);
        ticketFirstDto.setBuyerTicket("thanh tam");
        ticketFirstDto.setBirthdayTicket("1995-01-01 17:05");
        ticketFirstDto.setEmailTicket("thanhtam@email.com");
        ticketFirstDto.setGenderTicket(true);
        ticketFirstDto.setPhoneTicket("32156156165156164");
        ticketFirstDto.setEmployeeTicketDto(null);


        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("http://localhost:8080/api/firstUpdate?idFlight=1&typeSeat=vip")
                        .content(this.objectMapper.writeValueAsString(ticketFirstDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void updateFirstTicket_all_24() throws Exception {
        TicketFirstDto ticketFirstDto = new TicketFirstDto();
        EmployeeTicketDto employeeTicketDto = new EmployeeTicketDto();
        employeeTicketDto.setId(1L);
        ticketFirstDto.setBuyerTicket("thanh tam");
        ticketFirstDto.setBirthdayTicket("1995-01-01 17:05");
        ticketFirstDto.setEmailTicket("thanhtam@email.com");
        ticketFirstDto.setGenderTicket(true);
        ticketFirstDto.setPhoneTicket("0796298932");
        ticketFirstDto.setEmployeeTicketDto(null);


        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("http://localhost:8080/api/firstUpdate?idFlight=1&typeSeat=vip")
                        .content(this.objectMapper.writeValueAsString(ticketFirstDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
}
