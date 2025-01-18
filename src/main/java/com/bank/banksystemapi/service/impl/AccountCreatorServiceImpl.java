package com.bank.banksystemapi.service.impl;

import com.bank.banksystemapi.entity.Account;
import com.bank.banksystemapi.entity.User;
import com.bank.banksystemapi.repository.AccountRepository;
import com.bank.banksystemapi.service.AccountCreatorService;
import com.bank.banksystemapi.service.UserService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Random;

@Service
public class AccountCreatorServiceImpl implements AccountCreatorService {
    private final UserService userService;
    private final AccountRepository accountRepository;
    private Random random = new Random();

    public AccountCreatorServiceImpl(UserService userService, AccountRepository accountRepository) {
        this.userService = userService;
        this.accountRepository = accountRepository;
    }

    @Override
    public Account createAccount(User user) {
        Account account = new Account(
                getUniqueCardNumber(),
                generateCvv(),
                0.0,
                true,
                new Timestamp(System.currentTimeMillis()),
                user
        );
        accountRepository.save(account);
        return account;
    }

    private String getUniqueCardNumber() {
        String cardNumber = generateCardNumber();

        while (accountRepository.existsByCardNumber(cardNumber)){
            cardNumber = generateCardNumber();
        }
        return cardNumber;
    }

    private String generateCvv() {
        StringBuilder cvvNumber = new StringBuilder();
        for(int i = 0; i < 4; i++) {
            cvvNumber.append(random.nextInt(10));
        }
        return cvvNumber.toString();
    }
    private String generateCardNumber() {
        StringBuilder cvvNumber = new StringBuilder();
        for(int i=0;i<16;++i){
            cvvNumber.append(random.nextInt(10));
        }
        return cvvNumber.toString();
    }
}
