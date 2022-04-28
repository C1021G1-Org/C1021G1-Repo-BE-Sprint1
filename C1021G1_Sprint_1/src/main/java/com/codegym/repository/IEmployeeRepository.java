package com.codegym.repository;

import com.codegym.dto.EmployeeFindIdDto;
import com.codegym.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
@Repository
@Transactional
public interface IEmployeeRepository extends JpaRepository<Employee , Long> {
    @Query(value = "SELECT employee.id,employee.address_employee, employee.code_employee,employee.del_flag_employee, employee.birthday_employee, employee.email_employee, employee.gender_employee, employee.name_employee, employee.phone_employee, employee.id_employee_type as employee_type_id FROM employee join employee_type on employee_type.id = employee.id_employee_type WHERE employee.id = ?", nativeQuery = true)
        EmployeeFindIdDto findEmployeeById(Long id);
    @Modifying
    @Query(value = "INSERT INTO employee( employee.address_employee, employee.code_employee ,employee.del_flag_employee , employee.birthday_employee ,employee.email_employee, employee.gender_employee, employee.name_employee, employee.phone_employee, employee.id_employee_type) VALUES (?,?,?,?,?,?,?,?,?) ", nativeQuery = true)
    void createEmployee( String address_Employee, String code_Employee, Boolean del_Flag_Employee, String birthday_Employee, String email_Employee, Boolean genderEmployee , String phone_Employee, String name_Employee, Long id_Employee_Type);
    @Modifying
    @Query(value = " UPDATE employee as e SET e.address_employee = ?, e.code_employee = ?,e.del_flag_employee = ?, e.birthday_employee = ?, e.email_employee=?, e.gender_employee = ?, e.phone_employee = ?,e.name_employee= ?, e.id_employee_type =? WHERE e.id = ? ", nativeQuery = true)
    void editEmployee(String addressEmployee, String codeEmployee, Boolean delFlagEmployee, String birthdayEmployee, String emailEmployee, Boolean genderEmployee , String phoneEmployee, String nameEmployee,  Long idEmployeeType, Long id);

}
