package com.codegym.repository;

import com.codegym.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ITicketRepository extends JpaRepository<Ticket, Long> {
    @Query (value = "UPDATE Ticket set Ticket.buyer := buyer , Ticket.email_ticket:= email_ticket where id := ?", nativeQuery = true)
    void editTicket(Long id, @Param("buyer") String buyer  , @Param("email_ticket") String email_ticket);

}
