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
public interface ITicketRepository extends JpaRepository<Ticket,Long> {


@Query(value = "select ticket.id,ticket.buyer,ticket.code_ticket,ticket.day_of_birth,ticket.del_flag_ticket,ticket.email_ticket,ticket.gender_ticket,ticket.phone_ticket,ticket.point_ticket,ticket.price_ticket,ticket.status_ticket,ticket.id_customer,ticket.id_employee,ticket.id_flight " +
        "from ticket " +
        "left join flight on ticket.id_flight = flight.id " +
        "left join seat on flight.id = seat.id_flight " +
        "left join seat_type on seat_type.id = seat.id_seat_type " +
        "where flight.id = :idFlight " +
        "and seat.status_seat = 0 " +
        "and seat_type.name_seat_type = :typeSeat " +
        "group by ticket.id ",nativeQuery = true)
    List<Ticket> getListNumberTicket(@Param("idFlight")Long idFlight,@Param("typeSeat")String typeSeat);



}
