package com.bank.banksystemapi.service;

import com.bank.banksystemapi.entity.Account;
import com.bank.banksystemapi.entity.User;

public interface AccountCreatorService {
    Account createAccount(User user);
}
