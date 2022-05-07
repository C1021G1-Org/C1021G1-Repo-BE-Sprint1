package com.codegym.model;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

public class JwtReponse {
    private String email;
    private Collection<? extends GrantedAuthority> roles;
    private String token;
    public JwtReponse() {
    }

    public JwtReponse(String token,String email, Collection<? extends GrantedAuthority> roles) {
        this.token = token;
        this.email = email;
        this.roles = roles;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Collection<? extends GrantedAuthority> getRoles() {
        return roles;
    }

    public void setRoles(Collection<? extends GrantedAuthority> roles) {
        this.roles = roles;
    }
}
