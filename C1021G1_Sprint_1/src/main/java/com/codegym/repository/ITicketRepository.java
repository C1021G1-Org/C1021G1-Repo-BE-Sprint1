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
    @Query (value = "UPDATE Ticket SET Ticket.buyer_ticket = ?1 , Ticket.email_ticket = ?2 where Ticket.id = ?3", nativeQuery = true)
    void editTicket( String buyer , String emailTicket ,Long id);

    @Query(value = "SELECT  Ticket.id, Ticket.birthday_ticket , Ticket.buyer_ticket, " +
                    "Ticket.code_ticket , Ticket.del_flag_ticket, " +
                    "Ticket.email_ticket , Ticket.gender_ticket , " +
                    "Ticket.phone_ticket , Ticket.point_ticket , " +
                    "Ticket.price_ticket , Ticket.status_ticket , " +
                    "Ticket.id_customer , Ticket.id_employee , " +
                    "Employee.name_employee , Ticket.id_seat , " +
                    "Seat.id_flight , Flight.from_flight , " +
                    "Flight.to_flight , Flight.date_start , seat_type.name_seat_type " +
                    "FROM ticket " +
                    "JOIN employee on Employee.id = Ticket .id_employee " +
                    "JOIN Seat on seat.id = ticket.id_seat " +
                    "JOIN seat_type on seat_type.id = Seat.id_seat_type " +
                    "JOIN Flight on Flight.id = Seat.id_flight " +
                    "WHERE Ticket.id = ? ", nativeQuery = true)
    Ticket findTicketById(Long id);
}
