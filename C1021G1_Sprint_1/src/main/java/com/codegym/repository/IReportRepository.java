package com.codegym.repository;

import com.codegym.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IReportRepository extends JpaRepository<Ticket,Long> {

}
