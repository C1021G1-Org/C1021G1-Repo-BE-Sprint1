package com.codegym.repository;

import com.codegym.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
public interface IEmployeeRepository extends JpaRepository<Employee, Long> {
    @Query(value = "SELECT id, address_employee, code_employee, birthday_employee, del_flag_employee, email_employee, gender_employee, name_employee, phone_employee, id_employee_type \n" +
        "FROM `employee` WHERE del_flag_employee = '1'",nativeQuery = true)
    Page<Employee> findAllEmployee(Pageable pageable);

    @Modifying
    @Query(value = "UPDATE `employee` SET del_flag_employee = 0 WHERE id = ? ", nativeQuery = true)
    void deleteEmployee(Long id);

    @Query(value = "SELECT id, address_employee, code_employee, birthday_employee, del_flag_employee, email_employee, gender_employee, name_employee, phone_employee, id_employee_type \n" +
            "FROM `employee` WHERE id = ?",nativeQuery = true)
    Optional<Employee> findEmployeeById(Long id);

    @Query(value = "SELECT id, address_employee, code_employee, birthday_employee, del_flag_employee, email_employee, gender_employee, name_employee, phone_employee, id_employee_type \n" +
            "FROM `employee` WHERE name_employee LIKE %?1% AND code_employee LIKE %?2% AND email_employee LIKE %?3%", nativeQuery = true)
    Page<Employee> findEmployeeByElementContaining(String name, String code, String email, Pageable pageable);

}
