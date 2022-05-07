package com.codegym.service.sercurity.impl;

import com.codegym.model.Role;
import com.codegym.repository.sercurity.RoleRepository;
import com.codegym.service.sercurity.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    RoleRepository roleRepository;
    @Override
    public Role findByRole(String role) {
        return roleRepository.findRoleByRole(role);
    }
}
