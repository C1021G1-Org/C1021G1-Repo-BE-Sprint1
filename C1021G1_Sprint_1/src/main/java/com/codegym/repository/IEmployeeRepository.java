package com.codegym.repository;

import com.codegym.model.Employee;
  import com.codegym.dto.EmployeeFindIdDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
  import org.springframework.stereotype.Repository;
  
import javax.transaction.Transactional;

import java.util.Optional;

@Repository
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
            "FROM `employee` WHERE del_flag_employee = '1' AND name_employee LIKE %?1% AND code_employee LIKE %?2% AND email_employee LIKE %?3% ", nativeQuery = true)
    Page<Employee> findEmployeeByElementContaining(String name, String code, String email, Pageable pageable);
  
    @Query(value = "SELECT employee.id,employee.address_employee, employee.code_employee,employee.del_flag_employee, employee.birthday_employee, employee.email_employee, employee.gender_employee, employee.name_employee, employee.phone_employee, employee.id_employee_type as employee_type_id FROM employee join employee_type on employee_type.id = employee.id_employee_type WHERE employee.id = ?", nativeQuery = true)
        EmployeeFindIdDto findEmployeeById(Long id);
  
    @Modifying
    @Query(value = "INSERT INTO employee( employee.address_employee, employee.code_employee ,employee.del_flag_employee , employee.birthday_employee ,employee.email_employee, employee.gender_employee, employee.name_employee, employee.phone_employee, employee.id_employee_type) VALUES (?,?,?,?,?,?,?,?,?) ", nativeQuery = true)
    void createEmployee( String address_Employee, String code_Employee, Boolean del_Flag_Employee, String birthdayEmployee, String email_Employee, Boolean genderEmployee , String phone_Employee, String name_Employee, Long id_Employee_Type);
  
    @Modifying
    @Query(value = " UPDATE employee as e SET e.address_employee = ?, e.code_employee = ?,e.del_flag_employee = ?, e.birthday_employee = ?, e.email_employee=?, e.gender_employee = ?, e.phone_employee = ?,e.name_employee= ?, e.id_employee_type =? WHERE e.id = ? ", nativeQuery = true)
    void editEmployee(String addressEmployee, String codeEmployee, Boolean delFlagEmployee, String birthdayEmployee, String emailEmployee, Boolean genderEmployee , String phoneEmployee, String nameEmployee,  Long idEmployeeType, Long id);

}
