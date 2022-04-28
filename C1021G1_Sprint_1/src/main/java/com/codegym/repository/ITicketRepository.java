package com.codegym.repository;

import com.codegym.model.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
public interface ITicketRepository extends JpaRepository<Ticket, Long> {

    @Query(value = "SELECT Ticket.id, Ticket.buyer, Ticket.codeTicket, Ticket.delFlagTicket, Ticket.emailTicket, Ticket.genderTicket, Ticket.phoneTicket, Ticket.pointTicket, Ticket.priceTicket, Ticket.statusTicketFROM Ticket WHERE del_flag_ticket = '1'", nativeQuery = true)
    Page<Ticket> findAllTicket(Pageable pageable);

    @Modifying
    @Query(value = "UPDATE Ticket SET del_flag_employee = 0 WHERE id = ? ", nativeQuery = true)
    void deleteTicketById(Long id);

    @Query(value = "SELECT Ticket.id, Ticket.buyer, Ticket.codeTicket, Ticket.delFlagTicket, Ticket.emailTicket, Ticket.genderTicket, Ticket.phoneTicket, Ticket.pointTicket, Ticket.priceTicket, Ticket.statusTicket FROM Ticket ", nativeQuery = true)
    Optional<Ticket> findTicketById(Long id);

    @Query(value = "SELECT Ticket.id, Ticket.buyer, Ticket.codeTicket, Ticket.delFlagTicket, Ticket.emailTicket, Ticket.genderTicket, Ticket.phoneTicket, Ticket.pointTicket, Ticket.priceTicket, Ticket.statusTicket " +
            "FROM Ticket WHERE Ticket.buyer LIKE %?1% AND Ticket.codeTicket LIKE %?2% AND Ticket.emailTicket LIKE %?3%", nativeQuery = true)
    Page<Ticket> findTicketByElementContaining(String buyer, String code, String email, Pageable pageable);

}
