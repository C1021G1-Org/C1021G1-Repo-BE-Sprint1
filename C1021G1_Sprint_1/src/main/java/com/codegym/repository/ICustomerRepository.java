package com.codegym.repository;

import com.codegym.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Long> {

    @Query(value = "select c from Customer c")
    Page<Customer> findAllByCustomer(Pageable pageable);

    @Modifying
    @Query("delete from Customer c where c.id = ?1")
    void deleteCustomerByIdCustomer(Long id);

    @Query(value = "select c from Customer c where c.nameCustomer like %:keyword% and c.emailCustomer like %:keyword% and c.dayOfBirth like %:keyword% and c.addressCustomer like %:keyword% " +
            "and c.phoneCustomer like %:keyword% and c.countries like %:keyword% and c.customerType like %:keyword%", nativeQuery = true)
    List<Customer> findByAllField(@Param("keyword") String keyword);

}
