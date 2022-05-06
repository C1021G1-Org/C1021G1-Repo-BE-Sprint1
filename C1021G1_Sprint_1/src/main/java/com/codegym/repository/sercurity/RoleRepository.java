package com.codegym.repository.sercurity;

import com.codegym.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role,Long> {
    @Query(value = "SELECT id, role FROM roles WHERE  ROLE =?",nativeQuery = true)
    Role findRoleByRole(String role);
}
