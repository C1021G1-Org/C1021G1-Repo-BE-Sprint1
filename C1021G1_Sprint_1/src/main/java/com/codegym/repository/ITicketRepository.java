package com.codegym.repository;

import com.codegym.model.Customer;
import com.codegym.model.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

@Repository
@Transactional

public interface ITicketRepository extends JpaRepository<Ticket, Long> {

    @Query(value = "SELECT\n" +
            " ticket.id,\n" +
            " ticket.birthday_ticket,\n" +
            " ticket.buyer_ticket,\n" +
            " ticket.code_ticket,\n" +
            " ticket.del_flag_ticket,\n" +
            " ticket.email_ticket,\n" +
            " ticket.gender_ticket,\n" +
            " ticket.phone_ticket,\n" +
            " ticket.point_ticket,\n" +
            " ticket.price_ticket,\n" +
            " ticket.status_ticket,\n" +
            " ticket.id_customer,\n" +
            " ticket.id_employee,\n" +
            " ticket.id_seat\n" +
            "FROM ticket\n" +
            "WHERE ticket.id_customer = ?", nativeQuery = true)
    List<Ticket> getAllTicketsByCustomerID(Long id);

    @Query(value = "SELECT\n" +
            " ticket.id,\n" +
            " ticket.birthday_ticket,\n" +
            " ticket.buyer_ticket,\n" +
            " ticket.code_ticket,\n" +
            " ticket.del_flag_ticket,\n" +
            " ticket.email_ticket,\n" +
            " ticket.gender_ticket,\n" +
            " ticket.phone_ticket,\n" +
            " ticket.point_ticket,\n" +
            " ticket.price_ticket,\n" +
            " ticket.status_ticket,\n" +
            " ticket.id_customer,\n" +
            " ticket.id_employee,\n" +
            " ticket.id_seat\n" +
            "FROM ticket\n" +
            "WHERE ticket.code_ticket=?", nativeQuery = true)
    Ticket findTicketByCodeTicket(String codeTicket);

    @Modifying
    @Query(value = "UPDATE `c1021g1_sprint_1`.`ticket` SET ticket.status_ticket =true WHERE ticket.code_ticket=?",nativeQuery = true)
    void payTicketByCodeTicket(String codeTicket);
    @Modifying
    @Query(value = "UPDATE `c1021g1_sprint_1`.`ticket` SET ticket.status_ticket =null WHERE ticket.code_ticket=?",nativeQuery = true)
    void abortTicketByCodeTicket(String codeTicket);

}
