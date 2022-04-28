package com.codegym.service;

import com.codegym.model.Ticket;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ITicketService {


    List<Ticket> getListNumberTicket(@Param("idFlight") Long idFlight, @Param("typeSeat") String typeSeat);

    Ticket getTicketByFlightIdAndTypeSeatAndTicketId(@Param("idFlight")Long idFlight,@Param("typeSeat") String typeSeat,@Param("idTicket")Long idTicket);


//    @Query (value = "UPDATE ticket set ticket.buyer := buyer , ticket.email_ticket:= email_ticket where id := ?", nativeQuery = true)
//    void editTicket(Long id, @Param("buyer") String buyer  , @Param("email_ticket") String email_ticket);


//    void fistCreatTicket()
}
