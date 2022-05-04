package com.codegym.repository;

import com.codegym.dto.TicketDto;
import com.codegym.model.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public interface ITicketRepository extends JpaRepository<Ticket, Long> {

    @Query(value = "SELECT  ticket.id as idTicket, ticket.birthday_ticket as birthdayTicket," +
            "               ticket.buyer_ticket as buyerTicket, seat_type.name_seat_type as nameSeatType, \n" +
            "\t             ticket.code_ticket as codeTicket, ticket.del_flag_ticket as delFlagTicket,\n" +
            "               ticket.email_ticket as emailTicket, ticket.gender_ticket as genderTicket,\n" +
            "               ticket.phone_ticket as phoneTicket, ticket.point_ticket as pointTicket,\n" +
            "               ticket.price_ticket as priceTicket, ticket.status_ticket as statusTicket,\n" +
            "               ticket.id_customer as idCustomer, ticket.id_employee as idEmployee,\n" +
            "               employee.name_employee as nameEmployee, ticket.id_seat as idSeat, \n" +
            "               seat.id_flight as idFlight, flight.from_flight as fromFlight,\n" +
            "               flight.to_flight as toFlight, flight.date_start as dateStart\n" +
            "                    FROM ticket  \n" +
            "                    JOIN employee on employee.id = ticket.id_employee\n" +
            "                    JOIN seat on seat.id = ticket.id_seat\n" +
            "                    JOIN seat_type on seat_type.id = seat.id_seat_type" +
            "                    JOIN flight on flight.id = seat.id_flight WHERE ticket.del_flag_ticket = 1 ",
                                                nativeQuery = true, countQuery = "SELECT COUNT(*) FROM ticket ")
    Page<TicketDto> findAllListTicket(Pageable pageable);

    @Modifying
    @Query(value = "UPDATE Ticket SET ticket.del_flag_ticket = 0 WHERE ticket.id = ? ", nativeQuery = true)
    void deleteTicketById(Long id);

    @Query(value = "SELECT  ticket.id as idTicket, ticket.birthday_ticket as birthdayTicket, ticket.buyer_ticket as buyerTicket,\n" +
            "\t             ticket.code_ticket as codeTicket, ticket.del_flag_ticket as delFlagTicket,\n" +
            "               ticket.email_ticket as emailTicket, ticket.gender_ticket as genderTicket,\n" +
            "               ticket.phone_ticket as phoneTicket, ticket.point_ticket as pointTicket,\n" +
            "               ticket.price_ticket as priceTicket, ticket.status_ticket as statusTicket,\n" +
            "               ticket.id_customer as idCustomer, ticket.id_employee as idEmployee,\n" +
            "               employee.name_employee as nameEmployee, ticket.id_seat as idSeat, \n" +
            "               seat.id_flight as idFlight, flight.from_flight as fromFlight,\n" +
            "               flight.to_flight as toFlight, flight.date_start as dateStart, seat_type.name_seat_type \n" +
            "                    FROM ticket  \n" +
            "                    JOIN employee on employee.id = ticket.id_employee\n" +
            "                    JOIN seat on seat.id = ticket.id_seat\n" +
            "                    JOIN seat_type on seat_type.id = seat.id_seat_type" +
            "                    JOIN flight on flight.id = seat.id_flight " +
            "WHERE ticket.id = ? ", nativeQuery = true)
    Optional<TicketDto> findTicketById(Long id);

    @Query(value = "SELECT  ticket.id as idTicket, ticket.birthday_ticket as birthdayTicket," +
            "               ticket.buyer_ticket as buyerTicket, seat_type.name_seat_type as nameSeatType, \n" +
            "\t             ticket.code_ticket as codeTicket, ticket.del_flag_ticket as delFlagTicket,\n" +
            "               ticket.email_ticket as emailTicket, ticket.gender_ticket as genderTicket,\n" +
            "               ticket.phone_ticket as phoneTicket, ticket.point_ticket as pointTicket,\n" +
            "               ticket.price_ticket as priceTicket, ticket.status_ticket as statusTicket,\n" +
            "               ticket.id_customer as idCustomer, ticket.id_employee as idEmployee,\n" +
            "               employee.name_employee as nameEmployee, ticket.id_seat as idSeat, \n" +
            "               seat.id_flight as idFlight, flight.from_flight as fromFlight,\n" +
            "               flight.to_flight as toFlight, flight.date_start as dateStart\n" +
            "                    FROM ticket  \n" +
            "                    JOIN employee on employee.id = ticket.id_employee\n" +
            "                    JOIN seat on seat.id = ticket.id_seat\n" +
            "                    JOIN seat_type on seat_type.id = seat.id_seat_type" +
            "                    JOIN flight on flight.id = seat.id_flight WHERE ticket.del_flag_ticket = 1 " +
            "GROUP BY ticket.id "
            , nativeQuery = true, countQuery = "SELECT COUNT(*) FROM ticket ")
    List<TicketDto> getAllTicketDTONotPagination();

    @Query(value = "SELECT  ticket.id as idTicket, ticket.birthday_ticket as birthdayTicket," +
            "               ticket.buyer_ticket as buyerTicket, seat_type.name_seat_type as nameSeatType, \n" +
            "\t             ticket.code_ticket as codeTicket, ticket.del_flag_ticket as delFlagTicket,\n" +
            "               ticket.email_ticket as emailTicket, ticket.gender_ticket as genderTicket,\n" +
            "               ticket.phone_ticket as phoneTicket, ticket.point_ticket as pointTicket,\n" +
            "               ticket.price_ticket as priceTicket, ticket.status_ticket as statusTicket,\n" +
            "               ticket.id_customer as idCustomer, ticket.id_employee as idEmployee,\n" +
            "               employee.name_employee as nameEmployee, ticket.id_seat as idSeat, \n" +
            "               seat.id_flight as idFlight, flight.from_flight as fromFlight,\n" +
            "               flight.to_flight as toFlight, flight.date_start as dateStart\n" +
            "                    FROM ticket  \n" +
            "                    JOIN employee on employee.id = ticket.id_employee\n" +
            "                    JOIN seat on seat.id = ticket.id_seat\n" +
            "                    JOIN seat_type on seat_type.id = seat.id_seat_type" +
            "                    JOIN flight on flight.id = seat.id_flight " +
            "WHERE ticket.buyer_ticket LIKE %:keyword% AND ticket.del_flag_ticket = 1 ", nativeQuery = true, countQuery = " SELECT COUNT(*) FROM ticket \n")
    Page<TicketDto> ticketByBuyer(@Param("keyword")String keyword, Pageable pageable);

    @Query(value = "SELECT  ticket.id as idTicket, ticket.birthday_ticket as birthdayTicket," +
            "               ticket.buyer_ticket as buyerTicket, seat_type.name_seat_type as nameSeatType, \n" +
            "\t             ticket.code_ticket as codeTicket, ticket.del_flag_ticket as delFlagTicket,\n" +
            "               ticket.email_ticket as emailTicket, ticket.gender_ticket as genderTicket,\n" +
            "               ticket.phone_ticket as phoneTicket, ticket.point_ticket as pointTicket,\n" +
            "               ticket.price_ticket as priceTicket, ticket.status_ticket as statusTicket,\n" +
            "               ticket.id_customer as idCustomer, ticket.id_employee as idEmployee,\n" +
            "               employee.name_employee as nameEmployee, ticket.id_seat as idSeat, \n" +
            "               seat.id_flight as idFlight, flight.from_flight as fromFlight,\n" +
            "               flight.to_flight as toFlight, flight.date_start as dateStart\n" +
            "                    FROM ticket  \n" +
            "                    JOIN employee on employee.id = ticket.id_employee\n" +
            "                    JOIN seat on seat.id = ticket.id_seat\n" +
            "                    JOIN seat_type on seat_type.id = seat.id_seat_type" +
            "                    JOIN flight on flight.id = seat.id_flight " +
            "WHERE flight.to_flight LIKE %:keyword% AND ticket.del_flag_ticket = 1 ", nativeQuery = true, countQuery = " SELECT COUNT(*) FROM ticket \n")
    Page<TicketDto> ticketToFlight(@Param("keyword")String keyword, Pageable pageable);


    @Query(value = "SELECT  ticket.id as idTicket, ticket.birthday_ticket as birthdayTicket," +
            "               ticket.buyer_ticket as buyerTicket, seat_type.name_seat_type as nameSeatType, \n" +
            "\t             ticket.code_ticket as codeTicket, ticket.del_flag_ticket as delFlagTicket,\n" +
            "               ticket.email_ticket as emailTicket, ticket.gender_ticket as genderTicket,\n" +
            "               ticket.phone_ticket as phoneTicket, ticket.point_ticket as pointTicket,\n" +
            "               ticket.price_ticket as priceTicket, ticket.status_ticket as statusTicket,\n" +
            "               ticket.id_customer as idCustomer, ticket.id_employee as idEmployee,\n" +
            "               employee.name_employee as nameEmployee, ticket.id_seat as idSeat, \n" +
            "               seat.id_flight as idFlight, flight.from_flight as fromFlight,\n" +
            "               flight.to_flight as toFlight, flight.date_start as dateStart\n" +
            "                    FROM ticket  \n" +
            "                    JOIN employee on employee.id = ticket.id_employee\n" +
            "                    JOIN seat on seat.id = ticket.id_seat\n" +
            "                    JOIN seat_type on seat_type.id = seat.id_seat_type" +
            "                    JOIN flight on flight.id = seat.id_flight " +
            "WHERE flight.from_flight LIKE %:keyword% AND ticket.del_flag_ticket = 1 ", nativeQuery = true, countQuery = " SELECT COUNT(*) FROM ticket \n")
    Page<TicketDto> ticketFromFlight(@Param("keyword")String keyword, Pageable pageable);


    @Query(value = "SELECT  ticket.id as idTicket, ticket.birthday_ticket as birthdayTicket," +
            "               ticket.buyer_ticket as buyerTicket, seat_type.name_seat_type as nameSeatType, \n" +
            "\t             ticket.code_ticket as codeTicket, ticket.del_flag_ticket as delFlagTicket,\n" +
            "               ticket.email_ticket as emailTicket, ticket.gender_ticket as genderTicket,\n" +
            "               ticket.phone_ticket as phoneTicket, ticket.point_ticket as pointTicket,\n" +
            "               ticket.price_ticket as priceTicket, ticket.status_ticket as statusTicket,\n" +
            "               ticket.id_customer as idCustomer, ticket.id_employee as idEmployee,\n" +
            "               employee.name_employee as nameEmployee, ticket.id_seat as idSeat, \n" +
            "               seat.id_flight as idFlight, flight.from_flight as fromFlight,\n" +
            "               flight.to_flight as toFlight, flight.date_start as dateStart\n" +
            "                    FROM ticket  \n" +
            "                    JOIN employee on employee.id = ticket.id_employee\n" +
            "                    JOIN seat on seat.id = ticket.id_seat\n" +
            "                    JOIN seat_type on seat_type.id = seat.id_seat_type" +
            "                    JOIN flight on flight.id = seat.id_flight " +
            "WHERE ticket.code_ticket LIKE %:keyword% AND ticket.del_flag_ticket = 1 ", nativeQuery = true, countQuery = " SELECT COUNT(*) FROM ticket \n")
    Page<TicketDto> ticketCodeTicket(@Param("keyword")String keyword, Pageable pageable);

}