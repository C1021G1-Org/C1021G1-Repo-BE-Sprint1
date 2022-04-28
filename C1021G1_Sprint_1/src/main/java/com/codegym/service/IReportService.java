package com.codegym.service;

import com.codegym.dto.IReport;
import com.codegym.dto.IReportEmployee;
import com.codegym.model.Ticket;

import java.util.List;

public interface IReportService {
    List<IReport> getAllReport(Integer month);
    List<Ticket> getAllTicket();
    List<IReportEmployee> getAllReportEmployee();
}
