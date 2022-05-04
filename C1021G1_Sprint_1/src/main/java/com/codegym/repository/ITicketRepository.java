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

    @Query(value ="SELECT ticket.id, ticket.birthday_ticket, ticket.buyer_ticket, ticket.code_ticket, ticket.del_flag_ticket,\n" +
            "        ticket.email_ticket, ticket.gender_ticket, ticket.phone_ticket, ticket.point_ticket, ticket.price_ticket,\n" +
            "        ticket.status_ticket, ticket.id_customer, ticket.id_employee, ticket.id_seat\n" +
            "        FROM ticket\n" +
            "        WHERE ticket.id_customer = ?1 ", nativeQuery = true,countQuery = "select count(*) from ticket")
    List<Ticket> getAllTicketsByCustomerID(Long id);
    @Query(value ="SELECT ticket_history.id, ticket_history.birthday_ticket, ticket_history.buyer_ticket, ticket_history.code_ticket, ticket_history.del_flag_ticket,\n" +
            "        ticket_history.email_ticket, ticket_history.gender_ticket, ticket_history.phone_ticket, ticket_history.point_ticket, ticket_history.price_ticket,\n" +
            "        ticket_history.status_ticket, ticket_history.id_customer, ticket_history.id_employee, ticket_history.id_seat\n" +
            "        FROM ticket_history\n" +
            "        WHERE ticket_history.id_customer = ?1", nativeQuery = true,countQuery = "select count(*) from ticket")
    List<Ticket> getHistoryTicketsByCustomerID(Long id);

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
//    UPDATE `c1021g1_sprint_1`.`ticket` SET `birthday_ticket` = null, `buyer_ticket` = null, `email_ticket` = null, `gender_ticket` = null, `phone_ticket` = null, `point_ticket` = '12', `price_ticket` = null, `status_ticket` = null, `id_customer` = null, `id_employee` = null WHERE (`id` = '4');
//    @Query(value = "UPDATE `c1021g1_sprint_1`.`ticket` SET ticket.status_ticket =null WHERE ticket.code_ticket=?",nativeQuery = true)
    @Query(value = "UPDATE `c1021g1_sprint_1`.`ticket` SET ticket.birthday_ticket = null, ticket.buyer_ticket = null, ticket.email_ticket = null, ticket.gender_ticket = null, ticket.phone_ticket = null, ticket.price_ticket = null, ticket.status_ticket = null, ticket.id_customer = null, ticket.id_employee = null WHERE ticket.code_ticket=?",nativeQuery = true)
    void abortTicketByCodeTicket(String codeTicket);

    @Modifying
    @Query(value = "UPDATE `c1021g1_sprint_1`.`ticket_history` SET ticket_history.status_ticket =true WHERE ticket_history.code_ticket=?",nativeQuery = true)
    void payHistoryTicketByCodeTicket(String codeTicket);
    @Modifying
    @Query(value = "UPDATE `c1021g1_sprint_1`.`ticket_history` SET ticket_history.status_ticket =null WHERE ticket_history.code_ticket=?",nativeQuery = true)
    void abortHistoryTicketByCodeTicketTicketByCodeTicket(String codeTicket);

}
