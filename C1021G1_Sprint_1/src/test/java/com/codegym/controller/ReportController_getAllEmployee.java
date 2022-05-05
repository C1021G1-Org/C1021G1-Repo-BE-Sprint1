package com.codegym.controller;

import com.codegym.dto.IReportEmployee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ReportController_getAllEmployee {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ReportController reportController;

    @Test
    public void getAllEmployee_5() {
        ResponseEntity<List<IReportEmployee>> responseEntity
                = this.reportController.getAllEmployee();
        Assertions.assertEquals(400, responseEntity.getStatusCodeValue());
    }

    @Test
    public void getAllEmployee_6() {
        ResponseEntity<List<IReportEmployee>> responseEntity
                = this.reportController.getAllEmployee();
        Assertions.assertEquals(200, responseEntity.getStatusCodeValue());;
        Assertions.assertEquals("5",
                responseEntity.getBody().get(0).getMonthEmployee());
        Assertions.assertEquals("240",
                responseEntity.getBody().get(0).getSumPoint());
        Assertions.assertEquals("Joni Dang",
                responseEntity.getBody().get(0).getName_employee());
    }
}
