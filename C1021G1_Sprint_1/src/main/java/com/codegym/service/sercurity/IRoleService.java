package com.codegym.service.sercurity;

import com.codegym.model.Role;

public interface IRoleService {
    Role findByRole(String role);
}
