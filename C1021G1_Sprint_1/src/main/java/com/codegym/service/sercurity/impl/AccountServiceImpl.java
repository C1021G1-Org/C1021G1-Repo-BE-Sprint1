package com.codegym.service.sercurity.impl;

import com.codegym.model.Account;
import com.codegym.repository.sercurity.IAccountRepository;
import com.codegym.service.sercurity.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements IAccountService {
    @Autowired
    IAccountRepository accountRepository;
    @Override
    public Account findAccountByEmail(String email) {
        return accountRepository.findAccountByEmail(email).orElse(null);
    }

    @Override
    public Boolean existAccountByEmail(String email) {
        Account account = accountRepository.findAccountByEmail(email).orElse(null);
        if(account != null){
            return true;
        }
        return false;
    }

    @Override
    public Boolean existAccountByPhone(String phone) {
        Account account = accountRepository.findAccountByPhone(phone).orElse(null);
        if(account != null){
            return true;
        }
        return false;
    }

    @Override
    public Boolean existAccountByIdCard(String idCard) {
        Account account = accountRepository.findAccountByIdCard(idCard).orElse(null);
        if(account != null){
            return true;
        }
        return false;
    }
    public Boolean existAccountByPassword(String password) {
        Account account = accountRepository.findAccountByPassword(password).orElse(null);

        if(account != null){
            return true;
        }
        return false;
    }

    @Override
    public void save(Account acc) {
        //address,birthday,confirm_password,email,full_name,gender,id_card,password,phone,country_id
//        accountRepository.saveAccount(acc.getAddress(), acc.getBirthday(), acc.getConfirmPassword(),acc.getEmail(),
//                acc.getFullName(),acc.getGender(),acc.getIdCard(), acc.getPhone(), acc.getPassword(), acc.getCountry());
//    }
        accountRepository.save(acc);
    }

}
