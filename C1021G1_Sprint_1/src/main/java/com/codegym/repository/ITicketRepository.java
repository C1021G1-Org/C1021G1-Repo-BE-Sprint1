package com.codegym.repository;

import com.codegym.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

@Repository
@Transactional
public interface ITicketRepository extends JpaRepository<Ticket, Long> {

    @Modifying
    @Query (value = "UPDATE Ticket SET Ticket.buyer = ?1 , Ticket.email_ticket = ?2 where Ticket.id = ?3", nativeQuery = true)
    void editTicket( String buyer , String emailTicket ,Long id);

    @Query (value = "select Ticket.id, Ticket.buyer, Ticket.code_ticket,Ticket.day_of_birth, Ticket.del_flag_ticket, " +
            "Ticket.email_ticket, Ticket.gender_ticket, Ticket.phone_ticket, Ticket.point_ticket," +
            " Ticket.price_ticket, Ticket.status_ticket,Ticket.id_customer,Ticket.id_employee,  Ticket.id_flight from Ticket" +
            " WHERE Ticket.id = ?", nativeQuery = true)
    Ticket findTicketById(Long id);
}
