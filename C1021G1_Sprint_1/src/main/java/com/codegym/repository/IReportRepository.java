package com.codegym.repository;

import com.codegym.dto.IReport;
import com.codegym.dto.IReportEmployee;
import com.codegym.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IReportRepository extends JpaRepository<Ticket,Long> {
    @Query(value = "SELECT * FROM Ticket " ,nativeQuery = true)
    List<Ticket> getAllTicket();


//
// @Query(value = " SELECT SUM(price_ticket)totalPrice,COUNT(ticket.point_ticket)poinTicket,MONTH(date_start)monthStartDate FROM seat\n" +
//         " INNER JOIN seat_type ON seat.id_seat_type = seat_type.id \n" +
//         " INNER JOIN flight ON seat.id_flight = flight.id \n" +
//         " INNER JOIN ticket ON ticket.id_flight = flight.id\n" +
//         " WHERE MONTH(date_start) = ? \n" +
//         " GROUP BY MONTH(date_start)\n" +
//         " ORDER BY MONTH(date_start);",nativeQuery = true)
//    List<IReport> getAllReport();

    @Query(value = " SELECT SUM(price_ticket)totalPrice,COUNT(ticket.point_ticket)poinTicket,MONTH(date_start)monthStartDate FROM seat\n" +
            " INNER JOIN seat_type ON seat.id_seat_type = seat_type.id \n" +
            " INNER JOIN flight ON seat.id_flight = flight.id \n" +
            " INNER JOIN ticket ON ticket.id_seat = ticket.id\n" +
            " where MONTH(date_start)\n" +
            " GROUP BY MONTH(date_start)\n" +
            " ORDER BY MONTH(date_start); ",nativeQuery = true)
    List<IReport> getAllReport();





    @Query(value = "SELECT employee.id, employee.name_employee,SUM(ticket.point_ticket)sumPoint,MONTH(date_start)monthEmployee \n" +
            "FROM employee\n" +
            "INNER JOIN ticket ON ticket.id_employee = employee.id\n" +
            "INNER JOIN seat ON seat.id = ticket.id_seat\n" +
            "INNER JOIN flight  ON seat.id_flight = seat.id\n" +
            "WHERE status_ticket = 1 AND ticket.email_ticket = employee.email_employee \n" +
            "GROUP BY employee.id ORDER BY SUM(ticket.point_ticket) DESC;",nativeQuery = true)
    List<IReportEmployee> getAllReportEmployee();


}
