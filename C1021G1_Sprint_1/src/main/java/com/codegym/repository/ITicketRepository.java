package com.codegym.repository;

import com.codegym.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface ITicketRepository extends JpaRepository<Ticket, Long> {


    @Query(value = "select ticket.id,ticket.buyer_ticket,ticket.birthday_ticket,ticket.code_ticket,ticket.del_flag_ticket,ticket.email_ticket,ticket.gender_ticket,ticket.phone_ticket,ticket.point_ticket,ticket.price_ticket,ticket.status_ticket,ticket.id_customer,ticket.id_employee,ticket.id_seat " +
            "from ticket " +
            "join seat on seat.id = ticket.id_seat " +
            "join flight on flight.id = seat.id_flight " +
            "join seat_type on seat_type.id = seat.id_seat_type " +
            "where flight.id = :idFlight " +
            "and seat.status_seat = 0 " +
            "and seat_type.name_seat_type = :typeSeat ", nativeQuery = true)
    List<Ticket> getListNumberTicket(@Param("idFlight") Long idFlight, @Param("typeSeat") String typeSeat);
//
//    @Query(value = "select ticket.id,ticket.buyer,ticket.code_ticket,ticket.birthday_ticket,ticket.del_flag_ticket,ticket.email_ticket,ticket.gender_ticket,ticket.phone_ticket,ticket.point_ticket,ticket.price_ticket,ticket.status_ticket,ticket.id_customer,ticket.id_employee,ticket.id_flight " +
//            "where ticket.id = :identity ",nativeQuery = true)
//    Ticket getTicketByIdTicket(@Param("idTicket") Long idTicket);


//    @Query (value = "UPDATE ticket set ticket.buyer := buyer , ticket.email_ticket:= email_ticket where id := ?", nativeQuery = true)
//    void editTicket(Long id, @Param("buyer") String buyer  , @Param("email_ticket") String email_ticket);

//    void firstEditTicket(@Param())



}
