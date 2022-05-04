package com.codegym.controller;

import com.codegym.dto.IReport;
import com.codegym.dto.IReportEmployee;
import com.codegym.model.Ticket;
import com.codegym.service.IReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class ReportController {

    @Autowired
    public IReportService iReportService;

    @GetMapping("/report-price")
    public ResponseEntity<List<IReport>> getAllReport(){
        List<IReport> iReportList = iReportService.getAllReport();
        if(iReportList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(iReportList,HttpStatus.OK);
    }

    @GetMapping("/report-employee")
    public ResponseEntity<List<IReportEmployee>> getAllEmployee(){
        List<IReportEmployee> list = iReportService.getAllReportEmployee();
        if(list.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(list,HttpStatus.OK);
    }


}
