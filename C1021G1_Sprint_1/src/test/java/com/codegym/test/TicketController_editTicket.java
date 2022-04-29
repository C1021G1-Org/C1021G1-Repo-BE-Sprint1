package com.codegym.test;

import com.codegym.controller.TicketController;
import com.codegym.dto.EditTicketDto;
import com.codegym.model.Ticket;
import com.codegym.service.ITicketService;
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
public class TicketController_editTicket {
    @Autowired
    private TicketController ticketController;

    @Autowired
    private ITicketService ticketService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void updateTicket_buyer_13() throws Exception {
        EditTicketDto editTicketDto = new EditTicketDto();
        editTicketDto.setEmailTicket("nguyenlongtien@gmail.com");
        editTicketDto.setBuyer(null);

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("/editTicket/1")
                        .content(this.objectMapper.writeValueAsString(editTicketDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void updateTicket_emailTicket_13() throws Exception {
        EditTicketDto editTicketDto = new EditTicketDto();
        editTicketDto.setEmailTicket(null);
        editTicketDto.setBuyer("Nguyễn Ngọc Long Tiên");

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("/editTicket/1")
                        .content(this.objectMapper.writeValueAsString(editTicketDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void updateTicket_buyer_14() throws Exception {
        EditTicketDto editTicketDto = new EditTicketDto();
        editTicketDto.setEmailTicket("nguyenlongtien@gmail.com");
        editTicketDto.setBuyer("");

        this.mockMvc
         .perform(MockMvcRequestBuilders
                .patch("/editTicket/1")
                .content(this.objectMapper.writeValueAsString(editTicketDto))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
        }

    @Test
    public void updateTicket_emailTicket_14() throws Exception {
        EditTicketDto editTicketDto = new EditTicketDto();
        editTicketDto.setEmailTicket("");
        editTicketDto.setBuyer("Nguyễn Ngọc Long Tiên");

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("/editTicket/1")
                        .content(this.objectMapper.writeValueAsString(editTicketDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void updateTicket_emailTicket_15() throws Exception {
        EditTicketDto editTicketDto = new EditTicketDto();
        editTicketDto.setEmailTicket("longtien");
        editTicketDto.setBuyer("Nguyễn Ngọc Long Tiên");

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("/editTicket/1")
                        .content(this.objectMapper.writeValueAsString(editTicketDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

   @Test
    public void updateTicket_buyer_15() throws Exception {
        EditTicketDto editTicketDto = new EditTicketDto();
        editTicketDto.setEmailTicket("longtien@gmail.com");
        editTicketDto.setBuyer("Nguyễn Ngọc1 Long Tiên");

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("/editTicket/1")
                        .content(this.objectMapper.writeValueAsString(editTicketDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }



    @Test
    public void updateTicket_buyer_16() throws Exception {
        EditTicketDto editTicketDto = new EditTicketDto();
        editTicketDto.setEmailTicket("longtien@gmail.com");
        editTicketDto.setBuyer("long tiên");

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("/editTicket/1")
                        .content(this.objectMapper.writeValueAsString(editTicketDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void updateTicket_buyer_17() throws Exception {
        EditTicketDto editTicketDto = new EditTicketDto();
        editTicketDto.setEmailTicket("longtien@gmail.com");
        editTicketDto.setBuyer("long tiên long tiênlong tiênlong tiênlong tiênlong tiênlong tiênlong tiênlong tiênlong tiênlong tiên ");

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("/editTicket/1")
                        .content(this.objectMapper.writeValueAsString(editTicketDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void updateTicket_18() throws Exception {
        EditTicketDto editTicketDto = new EditTicketDto();
        editTicketDto.setEmailTicket("longtien@gmail.com");
        editTicketDto.setBuyer("Nguyễn Ngọc Long Tiên");

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("/editTicket/1")
                        .content(this.objectMapper.writeValueAsString(editTicketDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }
    }
