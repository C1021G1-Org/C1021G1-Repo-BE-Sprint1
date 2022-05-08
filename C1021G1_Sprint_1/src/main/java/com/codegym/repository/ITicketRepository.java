package com.codegym.repository;

import com.codegym.model.Employee;
import com.codegym.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
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
}
