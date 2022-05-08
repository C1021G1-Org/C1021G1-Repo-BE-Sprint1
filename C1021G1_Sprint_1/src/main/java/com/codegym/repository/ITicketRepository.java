package com.codegym.repository;


import com.codegym.model.Employee;
import com.codegym.model.Ticket;


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


//import javax.transaction.Transactional;

import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;

import java.util.List;

@Repository
@Transactional
public interface ITicketRepository extends JpaRepository<Ticket, Long> {


    @Query(value = "select ticket.id,ticket.buyer_ticket,ticket.birthday_ticket,ticket.code_ticket,ticket.del_flag_ticket,ticket.email_ticket,ticket.date_ticket,ticket.gender_ticket,ticket.id_card,ticket.phone_ticket,ticket.point_ticket,ticket.price_ticket,ticket.status_ticket,ticket.id_customer,ticket.id_employee,ticket.id_seat " +
            "from ticket " +
            "join seat on seat.id = ticket.id_seat " +
            "join flight on flight.id = seat.id_flight " +
            "join seat_type on seat_type.id = seat.id_seat_type " +
            "where seat.id_flight = :idFlight " +
            "and seat.status_seat = 0 " +
            "and seat_type.name_seat_type = :typeSeat " +
            "and del_flag_ticket = 1 " +
            "limit 5", nativeQuery = true)
    List<Ticket> getListNumberTicket(@Param("idFlight") Long idFlight, @Param("typeSeat") String typeSeat);


    @Query(value = "select ticket.id,ticket.buyer_ticket,ticket.birthday_ticket,ticket.code_ticket,ticket.del_flag_ticket,ticket.email_ticket,ticket.date_ticket,ticket.gender_ticket,ticket.phone_ticket,ticket.id_card,ticket.point_ticket,ticket.price_ticket,ticket.status_ticket,ticket.id_customer,ticket.id_employee,ticket.id_seat " +
            "from ticket " +
            "join seat on seat.id = ticket.id_seat " +
            "join flight on flight.id = seat.id_flight " +
            "join seat_type on seat_type.id = seat.id_seat_type " +
            "where flight.id = :idFlight " +
            "and seat.status_seat = 0 " +
            "and seat_type.name_seat_type = :typeSeat " +
            "and ticket.id = :idTicket " +
            "and ticket.del_flag_ticket = 1", nativeQuery = true)
    Ticket getTicketByFlightIdAndTypeSeatAndTicketId(@Param("idFlight") Long idFlight, @Param("typeSeat") String typeSeat, @Param("idTicket") Long idTicket);

    @Query(value = "select ticket.id,ticket.buyer_ticket,ticket.birthday_ticket,ticket.code_ticket,ticket.del_flag_ticket,ticket.email_ticket,ticket.date_ticket,ticket.gender_ticket,ticket.phone_ticket,ticket.id_card,ticket.point_ticket,ticket.price_ticket,ticket.status_ticket,ticket.id_customer,ticket.id_employee,ticket.id_seat " +
            "from ticket " +
            "where ticket.id = :idTicket"
            , nativeQuery = true)
    Ticket getTicketAddHistory(@Param("idTicket") Long idTicket);


    @Modifying
    @Query(value = "update ticket " +
            "join seat on seat.id = ticket.id_seat " +
            "set seat.status_seat = 1 " +
            ",buyer_ticket = :buyer " +
            ",birthday_ticket = :birthDay " +
            ",email_ticket = :email " +
            ",gender_ticket = :gender " +
            ",phone_ticket = :phone " +
            ",status_ticket = 0 " +
            ",date_ticket = :dateTicket " +
            ",id_employee = :employee " +
            ",id_customer = :customer" +
            ",price_ticket = :price " +
            ",id_card = :idCard " +
            "where ticket.id = :idTicket " +
            "and del_flag_ticket = 1 " +
            "and ticket.status_ticket is null", nativeQuery = true)
    void updateTicketByIdTicketAndIdEmployee(
            @Param("buyer") String buyer,
            @Param("birthDay") String birthDay,
            @Param("email") String email,
            @Param("gender") Boolean gender,
            @Param("phone") String phone,
            @Param("dateTicket") String dateTicket,
            @Param("price") Double price,
            @Param("idCard") String idCard,
            @Param("employee") Long employee,
            @Param("customer") Long customer,
            @Param("idTicket") Long idTicket

    );

    //    INSERT INTO ticket_history ( `birthday_ticket`, `buyer_ticket`, `code_ticket`, `del_flag_ticket`, `email_ticket`, `gender_ticket`, `phone_ticket`, `point_ticket`, `price_ticket`, `status_ticket`, `id_customer`, `id_employee`, `id_seat`, `date_ticket`, `id_card`) VALUES
//            ( '1995-01-01', 'thanh tam23', 'TK-016', b'1', 'nguyen@gmail.com', b'1','796238932', '2', '500000', b'0', NULL,'1', '16','1995-01-01','123456789')
    @Modifying
    @Query(value = "INSERT INTO ticket_history (birthday_ticket, buyer_ticket, code_ticket, del_flag_ticket, email_ticket, gender_ticket, " +
            " phone_ticket, point_ticket, price_ticket, status_ticket, id_customer, id_employee, id_seat, date_ticket, id_card) VALUES " +
            " (:birthdayTicket, :buyerTicket, :codeTicket, :delFlagTicket, :emailTicket, :genderTicket, :phoneTicket , :pointTicket, :priceTicket, :statusTicket, :idCustomer, :idEmployee, :idSeat, :dateTicket, :idCard) ", nativeQuery = true)
    void addTicketHistory(
            @Param("birthdayTicket") String birthdayTicket,
            @Param("buyerTicket") String buyerTicket,
            @Param("codeTicket") String codeTicket,
            @Param("delFlagTicket") Boolean delFlagTicket,
            @Param("emailTicket") String emailTicket,
            @Param("genderTicket") Boolean genderTicket,
            @Param("phoneTicket") String phoneTicket,
            @Param("pointTicket") Integer pointTicket,
            @Param("priceTicket") Double priceTicket,
            @Param("statusTicket") Boolean statusTicket,
            @Param("idCustomer") Long idCustomer,
            @Param("idEmployee") Long idEmployee,
            @Param("idSeat") Long idSeat,
            @Param("dateTicket") String dateTicket,
            @Param("idCard") String idCard);


//    @Query(value = "select ticket.id,ticket.buyer_ticket,ticket.birthday_ticket,ticket.code_ticket,ticket.email_ticket,ticket.gender_ticket,ticket.phone_ticket,ticket.price_ticket,ticket.status_ticket,ticket.id_employee,ticket.id_seat " +
//            "from ticket ", nativeQuery = true)
//    Ticket getTicketDtoById();


    @Query(value = "select employee.id from employee " +
            "where employee.email_employee = :email " +
            "and employee.del_flag_employee = 1 ",nativeQuery = true)
    Long getIdEmployeeByEmailRole(@Param("email")String emailEmployee);

    @Query(value = "select customer.id from customer " +
            "where customer.email_customer = :email " +
            "and cusromer.del_flag_employee = 1 ",nativeQuery = true)
    Long getIdCustomerEmailRole(@Param("email")String emailCustomer);




    //    SonNH lấy list không trả về page
    @Query(value = "SELECT ticket.id, ticket.birthday_ticket, ticket.buyer_ticket, ticket.code_ticket,ticket.date_ticket, ticket.del_flag_ticket,\n" +
            "        ticket.email_ticket, ticket.gender_ticket,ticket.id_card, ticket.phone_ticket, ticket.point_ticket, ticket.price_ticket,\n" +
            "        ticket.status_ticket, ticket.id_customer, ticket.id_employee, ticket.id_seat\n" +
            "        FROM ticket\n" +
            "        WHERE ticket.id_customer = ?1 ", nativeQuery = true, countQuery = "select count(*) from ticket")
    List<Ticket> getAllTicketsByCustomerID(Long id);

    @Query(value = "SELECT ticket_history.id, ticket_history.birthday_ticket, ticket_history.buyer_ticket, ticket_history.code_ticket,ticket_history.date_ticket, ticket_history.del_flag_ticket,\n" +
            "        ticket_history.email_ticket, ticket_history.gender_ticket,ticket_history.id_card, ticket_history.phone_ticket, ticket_history.point_ticket, ticket_history.price_ticket,\n" +
            "        ticket_history.status_ticket, ticket_history.id_customer, ticket_history.id_employee, ticket_history.id_seat\n" +
            "        FROM ticket_history\n" +
            "        WHERE ticket_history.id_customer = ?1", nativeQuery = true, countQuery = "select count(*) from ticket")
    List<Ticket> getHistoryTicketsByCustomerID(Long id);

    //    SonNH lấy list trả về page
    @Query(value = "SELECT ticket.id, ticket.birthday_ticket, ticket.buyer_ticket, ticket.code_ticket,ticket.date_ticket, ticket.del_flag_ticket,\n" +
            "        ticket.email_ticket, ticket.gender_ticket,ticket.id_card, ticket.phone_ticket, ticket.point_ticket, ticket.price_ticket,\n" +
            "        ticket.status_ticket, ticket.id_customer, ticket.id_employee, ticket.id_seat\n" +
            "        FROM ticket\n" +
            "        WHERE ticket.id_customer = ?1 ", nativeQuery = true, countQuery = "select count(*) from ticket")
    Page<Ticket> getAllTicketsByCustomerIDPage(Long id, Pageable pageable);

    @Query(value = "SELECT ticket_history.id, ticket_history.birthday_ticket, ticket_history.buyer_ticket, ticket_history.code_ticket,ticket_history.date_ticket, ticket_history.del_flag_ticket,\n" +
            "        ticket_history.email_ticket, ticket_history.gender_ticket,ticket_history.id_card, ticket_history.phone_ticket, ticket_history.point_ticket, ticket_history.price_ticket,\n" +
            "        ticket_history.status_ticket, ticket_history.id_customer, ticket_history.id_employee, ticket_history.id_seat\n" +
            "        FROM ticket_history\n" +
            "        WHERE ticket_history.id_customer = ?1", nativeQuery = true, countQuery = "select count(*) from ticket")
    Page<Ticket> getHistoryTicketsByCustomerIDPage(Long id, Pageable pageable);


    @Query(value = "SELECT\n" +
            " ticket.id,\n" +
            " ticket.birthday_ticket,\n" +
            " ticket.buyer_ticket,\n" +
            " ticket.code_ticket,ticket.date_ticket,\n" +
            " ticket.del_flag_ticket,\n" +
            " ticket.email_ticket,\n" +
            " ticket.gender_ticket,ticket.id_card,\n" +
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
    @Query(value = "UPDATE `c1021g1_sprint_1`.`ticket` SET ticket.birthday_ticket = null,ticket.id_card=null ,ticket.date_ticket=null , ticket.buyer_ticket = null, ticket.email_ticket = null, ticket.gender_ticket = null, ticket.phone_ticket = null, ticket.price_ticket = null, ticket.status_ticket = null, ticket.id_customer = null, ticket.id_employee = null WHERE ticket.code_ticket=?", nativeQuery = true)
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

    @Query(value = " SELECT ticket.id, ticket.birthday_ticket," +
            "               ticket.buyer_ticket, seat_type.name_seat_type, \n" +
            "\t             ticket.code_ticket, ticket.del_flag_ticket,\n" +
            "               ticket.email_ticket, ticket.gender_ticket,\n" +
            "               ticket.phone_ticket, ticket.point_ticket,\n" +
            "               ticket.price_ticket, ticket.status_ticket as statusTicket,\n" +
            "               ticket.id_customer, ticket.id_employee,\n" +
            "               employee.name_employee, ticket.id_seat, \n" +
            "               seat.id_flight, flight.from_flight,\n" +
            "               flight.to_flight, flight.date_start\n" +
            "                    FROM ticket  \n" +
            "                    JOIN employee on employee.id = ticket.id_employee\n" +
            "                    JOIN seat on seat.id = ticket.id_seat\n" +
            "                    JOIN seat_type on seat_type.id = seat.id_seat_type" +
            "                    JOIN flight on flight.id = seat.id_flight WHERE ticket.del_flag_ticket = 1 " +
            "GROUP BY ticket.id "
            , nativeQuery = true, countQuery = "SELECT COUNT(*) FROM ticket ")
    Page<TicketDto> getAllTicketDTONotPagination(Pageable pageable);

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
            "WHERE ticket.buyer_ticket LIKE %:keyword% AND ticket.del_flag_ticket = 1 ", nativeQuery = true,
            countQuery = " SELECT COUNT(*) FROM (SELECT ticket.id as idTicket, ticket.birthday_ticket as birthdayTicket, " +
                    "                                   ticket.buyer_ticket as buyerTicket, seat_type.name_seat_type as nameSeatType, " +
                    "                                   ticket.code_ticket as codeTicket, ticket.del_flag_ticket as delFlagTicket, " +
                    "                                   ticket.email_ticket as emailTicket, ticket.gender_ticket as genderTicket, " +
                    "                                   ticket.phone_ticket as phoneTicket, ticket.point_ticket as pointTicket, " +
                    "                                   ticket.price_ticket as priceTicket, ticket.status_ticket as statusTicket, " +
                    "                                   ticket.id_customer as idCustomer, ticket.id_employee as idEmployee, " +
                    "                                   employee.name_employee as nameEmployee, ticket.id_seat as idSeat,  " +
                    "                                   seat.id_flight as idFlight, flight.from_flight as fromFlight, " +
                    "                                   flight.to_flight as toFlight, flight.date_start as dateStart " +
                    "                                       FROM ticket " +
                    "                                       JOIN employee on employee.id = ticket.id_employee " +
                    "                                       JOIN seat on seat.id = ticket.id_seat " +
                    "                                       JOIN seat_type on seat_type.id = seat.id_seat_type " +
                    "                                       JOIN flight on flight.id = seat.id_flight  " +
                    "                                       WHERE ticket.buyer_ticket LIKE %:keyword% AND ticket.del_flag_ticket = 1 ) as buyer ")

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
            "WHERE flight.to_flight LIKE %:keyword% AND ticket.del_flag_ticket = 1 ", nativeQuery = true,
            countQuery = " SELECT COUNT(*) FROM (SELECT ticket.id as idTicket, ticket.birthday_ticket as birthdayTicket, " +
                    "                                   ticket.buyer_ticket as buyerTicket, seat_type.name_seat_type as nameSeatType, " +
                    "                                   ticket.code_ticket as codeTicket, ticket.del_flag_ticket as delFlagTicket, " +
                    "                                   ticket.email_ticket as emailTicket, ticket.gender_ticket as genderTicket, " +
                    "                                   ticket.phone_ticket as phoneTicket, ticket.point_ticket as pointTicket, " +
                    "                                   ticket.price_ticket as priceTicket, ticket.status_ticket as statusTicket, " +
                    "                                   ticket.id_customer as idCustomer, ticket.id_employee as idEmployee, " +
                    "                                   employee.name_employee as nameEmployee, ticket.id_seat as idSeat,  " +
                    "                                   seat.id_flight as idFlight, flight.from_flight as fromFlight, " +
                    "                                   flight.to_flight as toFlight, flight.date_start as dateStart " +
                    "                                       FROM ticket " +
                    "                                       JOIN employee on employee.id = ticket.id_employee " +
                    "                                       JOIN seat on seat.id = ticket.id_seat " +
                    "                                       JOIN seat_type on seat_type.id = seat.id_seat_type " +
                    "                                       JOIN flight on flight.id = seat.id_flight  " +
                    "                                       WHERE flight.to_flight LIKE %:keyword% AND ticket.del_flag_ticket = 1 ) as toFlight ")

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
            "WHERE flight.from_flight LIKE %:keyword% AND ticket.del_flag_ticket = 1 ", nativeQuery = true,
            countQuery = " SELECT COUNT(*) FROM (SELECT ticket.id as idTicket, ticket.birthday_ticket as birthdayTicket, " +
                    "                                   ticket.buyer_ticket as buyerTicket, seat_type.name_seat_type as nameSeatType, " +
                    "                                   ticket.code_ticket as codeTicket, ticket.del_flag_ticket as delFlagTicket, " +
                    "                                   ticket.email_ticket as emailTicket, ticket.gender_ticket as genderTicket, " +
                    "                                   ticket.phone_ticket as phoneTicket, ticket.point_ticket as pointTicket, " +
                    "                                   ticket.price_ticket as priceTicket, ticket.status_ticket as statusTicket, " +
                    "                                   ticket.id_customer as idCustomer, ticket.id_employee as idEmployee, " +
                    "                                   employee.name_employee as nameEmployee, ticket.id_seat as idSeat,  " +
                    "                                   seat.id_flight as idFlight, flight.from_flight as fromFlight, " +
                    "                                   flight.to_flight as toFlight, flight.date_start as dateStart " +
                    "                                       FROM ticket " +
                    "                                       JOIN employee on employee.id = ticket.id_employee " +
                    "                                       JOIN seat on seat.id = ticket.id_seat " +
                    "                                       JOIN seat_type on seat_type.id = seat.id_seat_type " +
                    "                                       JOIN flight on flight.id = seat.id_flight  " +
                    "                      WHERE flight.from_flight LIKE %:keyword% AND ticket.del_flag_ticket = 1 ) as formFlight")

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
            "WHERE ticket.code_ticket LIKE %:keyword% AND ticket.del_flag_ticket = 1 ", nativeQuery = true,
            countQuery = " SELECT COUNT(*) FROM (SELECT  ticket.id as idTicket, ticket.birthday_ticket as birthdayTicket," +
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
                    "                    JOIN seat_type on seat_type.id = seat.id_seat_type " +
                    "                    JOIN flight on flight.id = seat.id_flight " +
                    "WHERE ticket.code_ticket LIKE %:keyword% AND ticket.del_flag_ticket = 1 ) as codeTicket")

    Page<TicketDto> ticketCodeTicket(@Param("keyword") String keyword, Pageable pageable);


}
