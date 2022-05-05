package com.codegym.service;

import com.codegym.dto.IReport;
import com.codegym.dto.IReportAirlineType;
import com.codegym.dto.IReportEmployee;
import com.codegym.model.Ticket;
import java.util.List;

public interface IReportService {
    List<IReport> getAllReport();
    List<Ticket> getAllTicket();
    List<IReportEmployee> getAllReportEmployee();
    List<IReportAirlineType>  getAllAirlineType();
}
