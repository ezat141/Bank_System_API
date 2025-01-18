package com.bank.banksystemapi.service;

import com.bank.banksystemapi.model.transaction.DepositRequest;
import com.bank.banksystemapi.model.transaction.WithdrawRequest;

public interface TransactionService {
    void deposit(DepositRequest depositRequest);
    void withdraw(WithdrawRequest withdrawRequest);
}
