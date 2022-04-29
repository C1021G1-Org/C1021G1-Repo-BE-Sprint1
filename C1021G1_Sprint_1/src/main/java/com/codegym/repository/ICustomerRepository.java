package com.codegym.repository;

import com.codegym.dto.CustomerDto;
import com.codegym.model.Countries;
import com.codegym.model.Customer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import com.codegym.model.CustomerType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


import java.util.List;

@Transactional
@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Long> {


    @Query(value = "select id, name_customer, gender_customer, birthday_customer, email_customer, phone_customer, del_flag_customer, " +
            "address_customer, point_customer, id_country ,id_customer_type, id_card_customer, image_customer from `customer` where del_flag_customer = '1'", nativeQuery = true)
    Page<Customer> findAllByCustomer(Pageable pageable);
    
    @Modifying
    @Query(value = "update `customer` SET del_flag_customer = 0 where id = ?", nativeQuery = true)
    void deleteCustomerByIdCustomer(Long id);

    @Query(value = "select id, name_customer, gender_customer, birthday_customer, email_customer, phone_customer, " +
            "address_customer, point_customer, id_country ,id_customer_type, id_card_customer, del_flag_customer, image_customer from `customer` where id = ?", nativeQuery = true)
    Customer findByIdCustomer(Long id);

    @Query(value = "select id, name_customer, gender_customer, birthday_customer, email_customer, phone_customer, address_customer, " +
            "point_customer, id_country ,id_customer_type, id_card_customer, del_flag_customer, image_customer from `customer` where name_customer like %:keyword% " +
            "or address_customer like %:keyword%  or gender_customer like %:keyword%  or birthday_customer like %:keyword%  or email_customer like %:keyword% " +
            "or phone_customer like %:keyword%  or point_customer like %:keyword% or id_customer_type like %:keyword%  or id_card_customer like %:keyword% ", nativeQuery = true)
    List<Customer> searchAllByFields(@Param("keyword") String keyword);


    @Transactional
    @Modifying
    @Query(value = "insert into customer(name_customer,phone_customer,gender_customer,email_customer," +
            "id_card_customer,birthday_customer,address_customer,id_customer_type,id_country,del_flag_customer) " +
            "values (?1,?2,?3,?4,?5,?6,?7,?8,?9,?10)", nativeQuery = true)
    void saveCustomer(String nameCustomer,
                      String phoneCustomer,
                      Boolean genderCustomer,
                      String emailCustomer,
                      String idCardCustomer,
                      String birthdayCustomer,
                      String addressCustomer,
                      Long customerType,
                      Long countries,
                      Boolean delFlagCustomer);


    @Modifying
    @Query(value = "update `customer` SET name_customer = ?1,phone_customer = ?2,gender_customer = ?3,email_customer = ?4,id_card_customer = ?5,birthday_customer= ?6,address_customer = ?7,id_customer_type = ?8,id_country = ?9,del_flag_customer = ?10 where id = ?11", nativeQuery = true)
    void updateCustomer(
            String nameCustomer,
            String phoneCustomer,
            Boolean genderCustomer,
            String emailCustomer,
            String idCardCustomer,
            String birthdayCustomer,
            String addressCustomer,
            Long customerType,
            Long countries,
            Boolean delFlagCustomer,
            Long id);
}
