package com.bank.banksystemapi.service.impl;

import com.bank.banksystemapi.entity.Account;
import com.bank.banksystemapi.entity.Transaction;
import com.bank.banksystemapi.entity.User;
import com.bank.banksystemapi.mapper.AccountMapper;
import com.bank.banksystemapi.mapper.TransactionMapper;
import com.bank.banksystemapi.model.account.AccountActivityResponse;
import com.bank.banksystemapi.model.account.AccountResponse;
import com.bank.banksystemapi.model.account.AccountTransactionsHistoryResponseDto;
import com.bank.banksystemapi.repository.AccountRepository;
import com.bank.banksystemapi.repository.TransactionRepository;
import com.bank.banksystemapi.service.AccountCreatorService;
import com.bank.banksystemapi.service.AccountService;
import com.bank.banksystemapi.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {
    private final UserService userService;
    private final AccountCreatorService accountCreatorService;
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final AccountMapper accountMapper;
    private final TransactionMapper transactionMapper;

    public AccountServiceImpl(UserService userService, AccountCreatorService accountCreatorService, AccountRepository accountRepository, TransactionRepository transactionRepository, AccountMapper accountMapper, TransactionMapper transactionMapper) {
        this.userService = userService;
        this.accountCreatorService = accountCreatorService;
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
        this.accountMapper = accountMapper;
        this.transactionMapper = transactionMapper;
    }

    @Override
    public AccountResponse createAccount() {
        User user = userService.getCurrentUser();
        Account createdAccount = accountCreatorService.createAccount(user);

        return accountMapper.toResponse(createdAccount);
    }

    @Override
    public List<AccountResponse> getUserAccounts() {
        User user = userService.getCurrentUser();
        List<Account> allUserAccounts = accountRepository.getAccountsByUserId(user.getId());
        return allUserAccounts.stream()
                .map(accountMapper::toResponse)
                .toList();
    }


/*
    @Override
    public List<AccountTransactionsHistoryResponseDto> getUserAccountTransactions(Long accountId) {
        User user = userService.getCurrentUser();
        List<Transaction> allUserTransactions = transactionRepository.getTransactionsHistoryByAccountId(accountId);


        return allUserTransactions.stream()
                .map(transactionMapper::toResponse)
                .collect(Collectors.toList());
    }*/

    @Override
    public List<AccountTransactionsHistoryResponseDto> getUserAccountTransactions() {
        User user = userService.getCurrentUser();
        Account userAccount = accountRepository.getAccountByUserId(user.getId());
        List<Transaction> allUserTransactions = transactionRepository.getTransactionsHistoryByAccountId(userAccount.getId());


        return allUserTransactions.stream()
                .map(transactionMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public AccountActivityResponse deactivateAccount(String cardNumber) {
        Account account = accountRepository.findByCardNumber(cardNumber);
        checkUserAuthentication(account);

        if (account.getStatus()){
            account.setStatus(false);
            accountRepository.save(account);
        }
        return AccountActivityResponse.builder()
                .message("The account deactivated")
                .timestamp(new Timestamp(System.currentTimeMillis()))
                .build();
    }

    @Override
    public AccountActivityResponse activateAccount(String cardNumber) {
        Account account = accountRepository.findByCardNumber(cardNumber);
        checkUserAuthentication(account);
        if (!account.getStatus()){
            account.setStatus(true);
            accountRepository.save(account);

        }
        return AccountActivityResponse.builder()
                .message("The account activated")
                .timestamp(new Timestamp(System.currentTimeMillis()))
                .build();
    }

    @Override
    public boolean isAccountExistsByCardNumberAndCVV(String cardNumber, String cvv) {

        return accountRepository.existsByCardNumberAndCVV(cardNumber, cvv);
    }

    @Override
    public Double viewAuthenticatedAccountBalance() {
        User user = userService.getCurrentUser();
        Account userAccount = accountRepository.getAccountByUserId(user.getId());
        return userAccount.getBalance();
    }

    @Override
    public String getAuthenticatedAccountCardNumber() {
        User user = userService.getCurrentUser();
        Account userAccount = accountRepository.getAccountByUserId(user.getId());
        return userAccount.getCardNumber();
    }

    @Override
    public boolean isAccountExistsByCardNumber(String cardNumber) {
        return accountRepository.existsByCardNumber(cardNumber);
    }

    @Override
    public Account findAccountById(Long accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new EntityNotFoundException("Account not found"));
    }

    private void checkUserAuthentication(Account account){
        User user = userService.getCurrentUser();
        if(!account.getUser().equals(user)){
            throw new IllegalArgumentException("Not Auth To See Transaction History");
        }
    }
}
