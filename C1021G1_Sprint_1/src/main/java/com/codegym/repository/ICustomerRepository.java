package com.codegym.repository;

import com.codegym.dto.CustomerDto;
import com.codegym.model.Countries;
import com.codegym.model.Customer;
import com.codegym.model.CustomerType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Long> {
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
                             Date birthdayCustomer,
                             String addressCustomer,
                            Long customerType,
                             Long countries,
                             Boolean delFlagCustomer);
}
