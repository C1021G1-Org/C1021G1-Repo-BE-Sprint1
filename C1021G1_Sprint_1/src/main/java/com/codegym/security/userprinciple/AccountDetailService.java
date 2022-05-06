package com.codegym.security.userprinciple;

import com.codegym.model.Account;
import com.codegym.service.sercurity.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountDetailService implements UserDetailsService {
    @Autowired
    IAccountService accountService;
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Account account = accountService.findAccountByEmail(email);
        if(account == null){
            throw new UsernameNotFoundException("there is not email: "+ email);
        }
        return AccountPrinciple.build(account);
    }
}
