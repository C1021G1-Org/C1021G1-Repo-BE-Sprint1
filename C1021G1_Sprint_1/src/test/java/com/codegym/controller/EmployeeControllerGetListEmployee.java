package com.codegym.controller;

import com.codegym.model.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class EmployeeControllerGetListEmployee {

    @Autowired
    private EmployeeController employeeController;

    @Test
    public void getListEmployee_1() {
        ResponseEntity<Page<Employee>> responseEntity = this.employeeController.getAllEmployee(PageRequest.of(0,10));
        Assertions.assertEquals(404,responseEntity.getStatusCodeValue());
    }

    @Test
    public void getListEmployee_2() {
        ResponseEntity<Page<Employee>> responseEntity = this.employeeController.getAllEmployee(PageRequest.of(0,10));
        Assertions.assertEquals(200,responseEntity.getStatusCodeValue());
        Assertions.assertEquals(2, responseEntity.getBody().getTotalPages());
        Assertions.assertEquals(17, responseEntity.getBody().getTotalElements());
        Assertions.assertEquals("Ngô Duy Bảo",
                responseEntity.getBody().getContent().get(9).getNameEmployee());
    }
}
