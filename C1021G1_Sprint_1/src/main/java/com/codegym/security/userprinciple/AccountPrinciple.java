package com.codegym.security.userprinciple;

import com.codegym.model.Account;
import com.codegym.model.Countries;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AccountPrinciple implements UserDetails {
    private Long id;
    private String email;
    private String password;
    private String confirmPassword;
    private String phone;
    private String fullName;
    private String birthday;
    private String address;
    private Boolean gender;
    private String idCard;
    private Countries country;

    private Collection<? extends GrantedAuthority> roles;

    public AccountPrinciple() {
    }

    public AccountPrinciple(String email, String password, String confirmPassword, String phone,
                            String fullName, String birthday, String address, Boolean gender, String idCard,
                            Countries country, Collection<? extends GrantedAuthority> roles) {
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.phone = phone;
        this.fullName = fullName;
        this.birthday = birthday;
        this.address = address;
        this.gender = gender;
        this.idCard = idCard;
        this.country = country;
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static AccountPrinciple build(Account account){
        List<GrantedAuthority> authorityList = account.getRoles().stream().map(role ->
            new SimpleGrantedAuthority(role.getRole().name())).collect(Collectors.toList());
        return new AccountPrinciple(
                account.getEmail(),
                account.getPassword(),
                account.getConfirmPassword(),
                account.getPhone(),
                account.getFullName(),
                account.getBirthday(),
                account.getAddress(),
                account.getGender(),
                account.getIdCard(),
                account.getCountry(),
                authorityList
        );
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
