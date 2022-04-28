package com.codegym.repository;

import com.codegym.model.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ITicketRepository extends JpaRepository<Ticket, Long> {




//    @Query(value = "SELECT id, address_employee, code_employee, birthday_employee, del_flag_employee, email_employee, gender_employee, name_employee, phone_employee, id_employee_type \n" +
//            "FROM `employee` WHERE del_flag_employee = '1'",nativeQuery = true)
//    Page<Employee> findAllEmployee(Pageable pageable);

    @Query(value = "SELECT id, code_ticket, del_flag_ticket, id_employee, id_flight FROM ticket WHERE del_flag_ticket = '1'",nativeQuery = true)
    Page<Ticket> findAllTicket(Pageable pageable);
}
