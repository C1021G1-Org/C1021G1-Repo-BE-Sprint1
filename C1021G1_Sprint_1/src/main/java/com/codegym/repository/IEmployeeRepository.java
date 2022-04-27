package com.codegym.repository;

import com.codegym.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface IEmployeeRepository extends JpaRepository<Employee , Long> {
    @Modifying
    @Query(value = "SELECT employee.id,employee.address_employee, employee.code_employee,employee.del_flag_employee, employee.birthday_employee, employee.email_employee, employee.gender_employee, employee.name_employee, employee.phone_employee, employee.id_employee_type FROM employee WHERE employee.id = ?", nativeQuery = true)
    Employee findEmployeeById(Long id);
    @Transactional
    @Modifying
    @Query(value = " UPDATE employee SET employee.address_employee = ?, employee.code_employee = ?, employee.birthday_employee = ?, employee.email_employee=?, employee.gender_employee = ?, employee.name_employee= ?, employee.phone_employee = ?, employee.id_employee_type =? WHERE employee.id = ? ", nativeQuery = true)
    void updateEmployee(String nameEmployee, String codeEmployee,Boolean genderEmployee,String birthdayEmployee,String phoneEmployee,String emailEmployee,String addressEmployee,Boolean delFlagEmployee,Long id);
}
