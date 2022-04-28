package com.codegym.service.sercurity;

import com.codegym.model.Account;

public interface IAccountService {
    Account findAccountByEmail(String email);
    Boolean existAccountByEmail(String email);

    void save(Account acc);
}
