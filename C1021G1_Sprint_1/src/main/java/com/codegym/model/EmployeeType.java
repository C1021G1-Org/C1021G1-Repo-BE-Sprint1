package com.codegym.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Set;

@Entity
public class EmployeeType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nameEmployeeType;

    @JsonBackReference(value = "employee_employeeType")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employeeType")
    private Set<Employee> employees;

    public EmployeeType() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameEmployeeType() {
        return nameEmployeeType;
    }

    public void setNameEmployeeType(String nameEmployeeType) {
        this.nameEmployeeType = nameEmployeeType;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }
}
