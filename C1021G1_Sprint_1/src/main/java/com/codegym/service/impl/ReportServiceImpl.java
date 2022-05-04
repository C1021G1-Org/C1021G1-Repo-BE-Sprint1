package com.codegym.service.impl;

import com.codegym.dto.IReport;
import com.codegym.dto.IReportEmployee;
import com.codegym.model.Ticket;
import com.codegym.repository.IReportRepository;
import com.codegym.service.IReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReportServiceImpl implements IReportService {

    @Autowired
    public IReportRepository iReportRepository;

    @Override
    public List<IReport> getAllReport() {
        return iReportRepository.getAllReport();
    }

    @Override
    public List<Ticket> getAllTicket() {
        return iReportRepository.getAllTicket();
    }

    @Override
    public List<IReportEmployee> getAllReportEmployee() {
        return iReportRepository.getAllReportEmployee();
    }
}
