package com.codegym.repository;

import com.codegym.model.Customer;
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

import java.util.Date;
import java.util.List;

@Transactional
@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Long> {

    /*LongLT hiển thị list */
    @Query(value = "select id, name_customer, gender_customer, birthday_customer, email_customer, phone_customer, del_flag_customer, " +
            "address_customer, point_customer, id_country ,id_customer_type, id_card_customer, image_customer from `customer` where del_flag_customer = '1'", nativeQuery = true)
    Page<Customer> findAllByCustomer(Pageable pageable);

    /*LongLT xoa customer */
    @Modifying
    @Query(value = "update `customer` SET del_flag_customer = 0 where id = ?", nativeQuery = true)
    void deleteCustomerByIdCustomer(Long id);

    /*TinhHD tìm id customer */
    @Query(value = "select id, name_customer, gender_customer, birthday_customer, email_customer, phone_customer, " +
            "address_customer, point_customer, id_country ,id_customer_type, id_card_customer, del_flag_customer, image_customer from `customer` where id = ?", nativeQuery = true)
    Customer findByIdCustomer(Long id);


//    @Query(value = "select id, name_customer, gender_customer, birthday_customer, email_customer, phone_customer, address_customer, " +
//            "point_customer, id_country ,id_customer_type, id_card_customer, del_flag_customer, image_customer from `customer` where name_customer like %:keyword% " +
//            "or address_customer like %:keyword%  or gender_customer like %:keyword%  or birthday_customer like %:keyword%  or email_customer like %:keyword% " +
//            "or phone_customer like %:keyword%  or id_customer_type like %:keyword%  or id_card_customer like %:keyword% ", nativeQuery = true)
//    List<Customer> searchAllByFields(@Param("keyword") String keyword);




    /*LongLT search customer */
    @Query(value = "select id, name_customer, gender_customer, birthday_customer, email_customer, phone_customer, address_customer, " +
            "point_customer, id_country ,id_customer_type, id_card_customer, del_flag_customer, image_customer from `customer` where name_customer like %:keyword% " +
            "or address_customer like %:keyword%  or gender_customer like %:keyword%  or birthday_customer like %:keyword%  or email_customer like %:keyword% " +
            "or phone_customer like %:keyword%  or point_customer like %:keyword% or id_customer_type like %:keyword%  or id_card_customer like %:keyword% ", nativeQuery = true)
    List<Customer> searchAllByFields(@Param("keyword") String keyword);


    /*TinhHD tao thông tinh khách hàng bời nhân viên */
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


    @Transactional

    /*TinhHD cập nhật thông tinh khách hàng bời nhân viên */

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



    @Query(value = "select id, name_customer, gender_customer, birthday_customer, email_customer, phone_customer, address_customer," +
            " point_customer, id_country ,id_customer_type, id_card_customer, del_flag_customer, image_customer from `customer` " +
            "where email_customer like  %:keyword% and del_flag_customer = 1", nativeQuery = true)
    Page<Customer> searchByEmail(@Param("keyword") String keyword, Pageable pageable);


    @Query(value = "select id, name_customer, gender_customer, birthday_customer, email_customer, phone_customer, address_customer," +
            " point_customer, id_country ,id_customer_type, id_card_customer, del_flag_customer, image_customer from `customer` " +
            "where name_customer like %:keyword% and del_flag_customer = 1", nativeQuery = true)
    Page<Customer> searchByName(@Param("keyword") String keyword, Pageable pageable);

    @Query(value = "select id, name_customer, gender_customer, birthday_customer, email_customer, phone_customer, address_customer," +
            " point_customer, id_country ,id_customer_type, id_card_customer, del_flag_customer, image_customer from `customer` " +
            "where address_customer like %:keyword% and del_flag_customer = 1", nativeQuery = true)
    Page<Customer> searchByAddress(@Param("keyword") String keyword, Pageable pageable);

    @Query(value = "select c.id, c.name_customer, c.gender_customer, c.birthday_customer, c.email_customer, c.phone_customer, c.address_customer, " +
            " c.point_customer, c.id_country , c.id_customer_type, c.id_card_customer, c.del_flag_customer, c.image_customer " +
            " from customer c " +
            " join countries " +
            " on c.id_country = countries.id " +
            " where countries.country like %:keyword% and del_flag_customer = 1", nativeQuery = true)
    Page<Customer> searchByCountry(@Param("keyword") String keyword, Pageable pageable);

    @Query(value = "select c.id, c.name_customer, c.gender_customer, c.birthday_customer, c.email_customer, c.phone_customer, c.address_customer, " +
            " c.point_customer, c.id_country , c.id_customer_type, c.id_card_customer, c.del_flag_customer, c.image_customer " +
            " from customer c " +
            " join customer_type " +
            " on c.id_customer_type = customer_type.id " +
            " where customer_type.name_customer_type like %:keyword%  and del_flag_customer = 1", nativeQuery = true)
    Page<Customer> searchByCustomerType(@Param("keyword") String keyword, Pageable pageable);

    @Query(value = "select id, name_customer, gender_customer, birthday_customer, email_customer, phone_customer, address_customer," +
            " point_customer, id_country ,id_customer_type, id_card_customer, del_flag_customer, image_customer from `customer` " +
            "where phone_customer like %:keyword%  and del_flag_customer = 1", nativeQuery = true)
    Page<Customer> searchByPhone(@Param("keyword") String keyword, Pageable pageable);

    @Query(value = "select id, name_customer, gender_customer, birthday_customer, email_customer, phone_customer, address_customer," +
            " point_customer, id_country ,id_customer_type, id_card_customer, del_flag_customer, image_customer from `customer` " +
            "where id_card_customer like %:keyword%  and del_flag_customer = 1", nativeQuery = true)
    Page<Customer> searchByIdCard(@Param("keyword") String keyword, Pageable pageable);

    @Query(value = "select id, name_customer, gender_customer, birthday_customer, email_customer, phone_customer, del_flag_customer, " +
            "address_customer, point_customer, id_country ,id_customer_type, id_card_customer, image_customer from `customer` where del_flag_customer = '1'", nativeQuery = true)
    List<Customer> getAllCustomerNotPagination();

    /*ThangDBX lấy dữ liệu của khách hàng  */
    @Query(value = "select *" +
            "from customer\n" +
            "join customer_type on customer.id_customer_type = customer_type.id\n" +
            "where customer.id = :id" , nativeQuery = true )
    Customer findCustomerByID(@Param("id") Long id );



    @Query(value = "select customer.id, customer.name_customer, customer.gender_customer, customer.birthday_customer, customer.email_customer, customer.phone_customer, " +
            "customer.address_customer, customer.id_country ,customer.id_customer_type, customer.id_card_customer, customer.del_flag_customer, customer.image_customer, customer.point_customer " +
            "from customer\n" +
            "join customer_type on customer.id_customer_type = customer_type.id\n" +
            "where customer.id = :id" , nativeQuery = true )
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
