package com.codegym.service.sercurity;

import com.codegym.model.Account;

public interface IAccountService {
    Account findAccountByEmail(String email);
    Boolean existAccountByEmail(String email);
    Boolean existAccountByPhone(String phone);
    Boolean existAccountByIdCard(String idCard);

    Boolean existAccountByPassword(String password);
    void save(Account acc);

}
