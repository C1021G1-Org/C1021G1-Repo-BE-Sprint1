package com.codegym.repository;


import com.codegym.model.Customer;

import com.codegym.dto.TicketDto;
import com.codegym.model.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

@Repository
@Transactional

public interface ITicketRepository extends JpaRepository<Ticket, Long> {

    //    SonNH lấy list không trả về page
    @Query(value = "SELECT ticket.id, ticket.birthday_ticket, ticket.buyer_ticket, ticket.code_ticket, ticket.del_flag_ticket,\n" +
            "        ticket.email_ticket, ticket.gender_ticket, ticket.phone_ticket, ticket.point_ticket, ticket.price_ticket,\n" +
            "        ticket.status_ticket, ticket.id_customer, ticket.id_employee, ticket.id_seat\n" +
            "        FROM ticket\n" +
            "        WHERE ticket.id_customer = ?1 ", nativeQuery = true, countQuery = "select count(*) from ticket")
    List<Ticket> getAllTicketsByCustomerID(Long id);

    @Query(value = "SELECT ticket_history.id, ticket_history.birthday_ticket, ticket_history.buyer_ticket, ticket_history.code_ticket, ticket_history.del_flag_ticket,\n" +
            "        ticket_history.email_ticket, ticket_history.gender_ticket, ticket_history.phone_ticket, ticket_history.point_ticket, ticket_history.price_ticket,\n" +
            "        ticket_history.status_ticket, ticket_history.id_customer, ticket_history.id_employee, ticket_history.id_seat\n" +
            "        FROM ticket_history\n" +
            "        WHERE ticket_history.id_customer = ?1", nativeQuery = true, countQuery = "select count(*) from ticket")
    List<Ticket> getHistoryTicketsByCustomerID(Long id);

    //    SonNH lấy list trả về page
    @Query(value = "SELECT ticket.id, ticket.birthday_ticket, ticket.buyer_ticket, ticket.code_ticket, ticket.del_flag_ticket,\n" +
            "        ticket.email_ticket, ticket.gender_ticket, ticket.phone_ticket, ticket.point_ticket, ticket.price_ticket,\n" +
            "        ticket.status_ticket, ticket.id_customer, ticket.id_employee, ticket.id_seat\n" +
            "        FROM ticket\n" +
            "        WHERE ticket.id_customer = ?1 ", nativeQuery = true, countQuery = "select count(*) from ticket")
    Page<Ticket> getAllTicketsByCustomerIDPage(Long id, Pageable pageable);

    @Query(value = "SELECT ticket_history.id, ticket_history.birthday_ticket, ticket_history.buyer_ticket, ticket_history.code_ticket, ticket_history.del_flag_ticket,\n" +
            "        ticket_history.email_ticket, ticket_history.gender_ticket, ticket_history.phone_ticket, ticket_history.point_ticket, ticket_history.price_ticket,\n" +
            "        ticket_history.status_ticket, ticket_history.id_customer, ticket_history.id_employee, ticket_history.id_seat\n" +
            "        FROM ticket_history\n" +
            "        WHERE ticket_history.id_customer = ?1", nativeQuery = true, countQuery = "select count(*) from ticket")
    Page<Ticket> getHistoryTicketsByCustomerIDPage(Long id, Pageable pageable);


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
    @Query(value = "UPDATE `c1021g1_sprint_1`.`ticket` SET ticket.status_ticket =true WHERE ticket.code_ticket=?", nativeQuery = true)
    void payTicketByCodeTicket(String codeTicket);

    @Modifying
//    UPDATE `c1021g1_sprint_1`.`ticket` SET `birthday_ticket` = null, `buyer_ticket` = null, `email_ticket` = null, `gender_ticket` = null, `phone_ticket` = null, `point_ticket` = '12', `price_ticket` = null, `status_ticket` = null, `id_customer` = null, `id_employee` = null WHERE (`id` = '4');
//    @Query(value = "UPDATE `c1021g1_sprint_1`.`ticket` SET ticket.status_ticket =null WHERE ticket.code_ticket=?",nativeQuery = true)
    @Query(value = "UPDATE `c1021g1_sprint_1`.`ticket` SET ticket.birthday_ticket = null, ticket.buyer_ticket = null, ticket.email_ticket = null, ticket.gender_ticket = null, ticket.phone_ticket = null, ticket.price_ticket = null, ticket.status_ticket = null, ticket.id_customer = null, ticket.id_employee = null WHERE ticket.code_ticket=?", nativeQuery = true)
    void abortTicketByCodeTicket(String codeTicket);

    @Modifying
    @Query(value = "UPDATE `c1021g1_sprint_1`.`ticket_history` SET ticket_history.status_ticket =true WHERE ticket_history.code_ticket=?", nativeQuery = true)
    void payHistoryTicketByCodeTicket(String codeTicket);

    @Modifying
    @Query(value = "UPDATE `c1021g1_sprint_1`.`ticket_history` SET ticket_history.status_ticket =null WHERE ticket_history.code_ticket=?", nativeQuery = true)
    void abortHistoryTicketByCodeTicketTicketByCodeTicket(String codeTicket);


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
    Page<TicketDto> ticketByBuyer(@Param("keyword") String keyword, Pageable pageable);

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
    Page<TicketDto> ticketToFlight(@Param("keyword") String keyword, Pageable pageable);


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
    Page<TicketDto> ticketFromFlight(@Param("keyword") String keyword, Pageable pageable);


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
    Page<TicketDto> ticketCodeTicket(@Param("keyword") String keyword, Pageable pageable);

}
