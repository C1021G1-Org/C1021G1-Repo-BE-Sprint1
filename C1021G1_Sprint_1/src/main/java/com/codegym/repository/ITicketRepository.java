package com.codegym.repository;

import com.codegym.model.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
public interface ITicketRepository extends JpaRepository<Ticket, Long> {

    @Query(value = "SELECT id, birthday_ticket, buyer_ticket, code_ticket, del_flag_ticket,\n" +
            "email_ticket, gender_ticket, phone_ticket, point_ticket, price_ticket, status_ticket, id_customer, id_employee,id_seat\n" +
            "FROM ticket WHERE del_flag_ticket = 1"
            , nativeQuery = true, countQuery = "SELECT COUNT(*) FROM ticket WHERE del_flag_ticket = '1'")
    Page<Ticket> findAllListTicket(Pageable pageable);

    @Modifying
    @Query(value = "UPDATE Ticket SET del_flag_ticket = 0 WHERE id = ? ", nativeQuery = true)
    void deleteTicketById(Long id);

    @Query(value = "SELECT " +
                  " id, birthday_ticket, buyer_ticket, code_ticket, del_flag_ticket, email_ticket, gender_ticket, phone_ticket, point_ticket, price_ticket, status_ticket, id_customer, id_employee,id_seat" +
                  " FROM ticket WHERE id = ? ", nativeQuery = true)
    Optional<Ticket> findTicketById(Long id);

    @Query(value = "SELECT id, birthday_ticket, buyer_ticket, code_ticket, del_flag_ticket,\n" +
            " email_ticket, gender_ticket, phone_ticket, point_ticket, price_ticket, status_ticket, id_customer, id_employee,id_seat\n" +
            " FROM ticket WHERE buyer_ticket like concat('%',:buyer_ticket,'%') AND code_ticket LIKE concat('%',:code_ticket,'%') AND email_ticket LIKE concat('%',:email_ticket,'%')", nativeQuery = true)
    Page<Ticket> findTicketByElementContaining(@Param("buyer_ticket") String buyer,@Param("code_ticket") String code,@Param("email_ticket") String email, Pageable pageable);

}
