package com.bank.banksystemapi.mapper;

import com.bank.banksystemapi.entity.Account;
import com.bank.banksystemapi.model.account.AccountResponse;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {
    public AccountResponse toResponse(Account account) {
        return AccountResponse.builder()
                .cardNumber(account.getCardNumber())
                .CVV(account.getCVV())
                .balance(account.getBalance())
                .createdAt(account.getCreatedAt())
                .status(account.getStatus())
                .build();
    }
}
