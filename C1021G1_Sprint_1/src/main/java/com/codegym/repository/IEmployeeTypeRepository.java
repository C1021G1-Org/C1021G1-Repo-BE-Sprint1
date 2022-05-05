package com.codegym.repository;

import com.codegym.model.EmployeeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface IEmployeeTypeRepository extends JpaRepository<EmployeeType, Long> {
    @Query(value = "SELECT id, name_employee_type FROM employee_type",nativeQuery = true)
    List<EmployeeType> findAllEmployeeType();
}