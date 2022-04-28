package com.codegym.repository;

import com.codegym.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer,Long> {

    /*ThangDBX lấy dữ liệu của khách hàng  */
    @Query(value = "select *" +
            "from customer\n" +
            "join customer_type on customer.id_customer_type = customer_type.id\n" +
            "where customer.id = :id" , nativeQuery = true )
    Customer findCustomerByID(@Param("id") Long id );

    @Query(value = "select id, name_customer, gender_customer, birthday_customer, email_customer, phone_customer, " +
            "address_customer, point_customer, id_country ,id_customer_type, id_card_customer, del_flag_customer, image_customer from `customer` where id = ?", nativeQuery = true)
    Customer findByIdPersonal(Long id);


    /*ThangDBX cập nhật dữ liệu của khách hàng  */

    @Modifying
    @Transactional
    @Query(value = "update customer c \n" +
            "set c.name_customer = ?1 , c.gender_customer = ?2, c.email_customer = ?3 , c.phone_customer = ?4 ,\n" +
            " c.birthday_customer = ?5 , c.id_card_customer = ?6, c.id_country = ?7 , c.address_customer = ?8 , c.image_customer = ?9\n" +
            " where c.id = ?10 ", nativeQuery = true)
    void updatePersonalInfo(
            String name,
            Boolean gender,
            String email,
            String phone,
            String birthday,
            String idCard,
            Long country,
            String address,
            String image,
            Long id
    );

    /* ThangDBX update customerType  */
    @Transactional
    @Modifying
    @Query(value = "update customer c \n" +
            "set c.id_customer_type = ?1 \n" +
            " where c.id = ?2 ", nativeQuery = true)
    void updateCustomerType(
            Long customerTypeId,
            Long customerId
    );


}
