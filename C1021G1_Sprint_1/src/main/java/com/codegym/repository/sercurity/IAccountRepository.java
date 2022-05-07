package com.codegym.repository.sercurity;

import com.codegym.model.Account;
import com.codegym.model.Countries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
public interface IAccountRepository extends JpaRepository<Account,Long> {
    @Query(value = "SELECT id,address, birthday, confirm_password, email, full_name, gender," +
            "id_card,password, phone, country_id FROM accounts WHERE email =?",nativeQuery = true)
    Optional<Account> findAccountByEmail(String email);
    @Modifying
    @Query(value = "INSERT INTO accounts (`address`, `birthday`, `confirm_password`, `email`, `full_name`, `gender`, `id_card`, `phone`,`password`, `country_id`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",nativeQuery = true)
    void saveAccount(String address, String birthday, String confirm_password, String email, String full_name, boolean gender, String id_card, String phone, String password, Countries country_id);

    @Query(value = "SELECT id,address, birthday, confirm_password, email, full_name, gender," +
            "id_card,password, phone, country_id FROM accounts WHERE id_card =?",nativeQuery = true)
    Optional<Account> findAccountByIdCard(String idCard);

    @Query(value = "SELECT id,address, birthday, confirm_password, email, full_name, gender," +
            "id_card,password, phone, country_id FROM accounts WHERE phone =?",nativeQuery = true)
    Optional<Account> findAccountByPhone(String phone);

    @Query(value = "SELECT id,address, birthday, confirm_password, email, full_name, gender," +
            "id_card,password, phone, country_id FROM accounts WHERE confirm_password =?",nativeQuery = true)
    Optional<Account> findAccountByPassword(String password);
}
