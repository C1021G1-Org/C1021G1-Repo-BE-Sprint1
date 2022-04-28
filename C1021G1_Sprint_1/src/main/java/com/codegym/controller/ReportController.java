package com.codegym.controller;

import com.codegym.dto.IReport;
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

    @GetMapping("/report-ticket")
    public ResponseEntity<List<Ticket>> getAllTicket(){
        List<Ticket> ticketList = iReportService.getAllTicket();
        if(ticketList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(ticketList,HttpStatus.OK);
    }


    @GetMapping("/report-price")
    public ResponseEntity<List<IReport>> getAllReport(@RequestParam Integer month){
        List<IReport> iReportList = iReportService.getAllReport(month);
        if(iReportList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(iReportList,HttpStatus.OK);
    }

    @GetMapping("/report-employee")
    public ResponseEntity<List<IReport>> getAllEmployee(){
        List<IReport> list = iReportService.getAllReportEmployee();
        if(list.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list,HttpStatus.OK);
    }


}
