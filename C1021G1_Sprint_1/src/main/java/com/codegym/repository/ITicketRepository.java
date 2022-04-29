package com.codegym.repository;

import com.codegym.dto.ListTicketDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
public interface ITicketRepository extends JpaRepository<com.codegym.model.Ticket, Long> {

    @Query(value = "SELECT ticket.id, ticket.birthday_ticket, ticket.buyer_ticket, ticket.code_ticket, ticket.del_flag_ticket,\n" +
            "ticket.email_ticket, ticket.gender_ticket, ticket.phone_ticket, ticket.point_ticket, ticket.price_ticket,\n" +
            "ticket.status_ticket, ticket.id_customer, ticket.id_employee, ticket.id_seat, seat.id_flight\n" +
            "FROM ticket\n" +
            "JOIN seat on ticket.id_seat = seat.id\n" +
            "JOIN flight on seat.id_flight = flight.id\n" +
            "WHERE del_flag_ticket = '1'"
            , nativeQuery = true, countQuery = "SELECT COUNT(*) FROM ticket WHERE del_flag_ticket = '1'")
    Page<com.codegym.model.Ticket> findAllListTicket(Pageable pageable);

    @Modifying
    @Query(value = "UPDATE Ticket SET del_flag_ticket = 0 WHERE id = ?", nativeQuery = true)
    void deleteTicketById(Long id);

    @Query(value = "SELECT " +
            " id, birthday_ticket, buyer_ticket, code_ticket, del_flag_ticket, email_ticket, gender_ticket, phone_ticket, point_ticket, price_ticket, status_ticket, id_customer, id_employee,id_seat" +
            " FROM ticket WHERE id = ? ", nativeQuery = true)
    Optional<com.codegym.model.Ticket> findTicketById(Long id);

    @Query(value = "SELECT ticket.id as idTicket, ticket.birthday_ticket as birthdayTicket, ticket.buyer_ticket as buyerTicket, ticket.code_ticket as codeTicket, ticket.del_flag_ticket as delFlagTicket,\n" +
            "ticket.email_ticket as emailTicket, ticket.gender_ticket as genderTicket, ticket.phone_ticket as phoneTicket, ticket.point_ticket as pointTicket, ticket.price_ticket as priceTicket,\n" +
            "ticket.status_ticket as statusTicket, ticket.id_customer as idCustomer, ticket.id_employee as idEmployee, ticket.id_seat as idSeat, seat.id_flight \n" +
            "FROM ticket \n" +
            "JOIN seat on seat.id = ticket.id_seat \n" +
            "JOIN flight on flight.id = seat.id_flight \n" +
            "WHERE ticket.buyer_ticket LIKE CONCAT('%',?1,'%') AND ticket.id_seat LIKE CONCAT('%',?2,'%') AND seat.id_flight LIKE CONCAT('%',?3,'%') ", nativeQuery = true, countQuery = " SELECT COUNT(*) \n" +
            "FROM ticket \n")

    Page<ListTicketDto> findTicketByElementContaining(String buyer, String code, String flight, Pageable pageable);

}