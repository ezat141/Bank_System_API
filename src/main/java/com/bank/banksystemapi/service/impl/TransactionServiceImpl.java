package com.bank.banksystemapi.service.impl;

import com.bank.banksystemapi.entity.Account;
import com.bank.banksystemapi.entity.Transaction;
import com.bank.banksystemapi.entity.enumTypes.TransactionType;
import com.bank.banksystemapi.model.transaction.DepositRequest;
import com.bank.banksystemapi.model.transaction.WithdrawRequest;
import com.bank.banksystemapi.repository.AccountRepository;
import com.bank.banksystemapi.repository.TransactionRepository;
import com.bank.banksystemapi.repository.UserRepository;
import com.bank.banksystemapi.service.AccountService;
import com.bank.banksystemapi.service.TransactionService;
import com.bank.banksystemapi.service.UserService;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.sql.Timestamp;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserService userService;
    private final AccountRepository accountRepository;
    private final AccountService accountService;

    public TransactionServiceImpl(TransactionRepository transactionRepository, UserService userService, AccountRepository accountRepository, AccountService accountService) {
        this.transactionRepository = transactionRepository;
        this.userService = userService;
        this.accountRepository = accountRepository;
        this.accountService = accountService;
    }
    @Override
    public void deposit(DepositRequest depositRequest) {
        Account account= validateAccountForDeposit(depositRequest);
        doDeposit(account, depositRequest.getAmount());




    }

    @Override
    public void withdraw(WithdrawRequest withdrawRequest) {
        Account account= validateAccountForWithdraw(withdrawRequest);
        doWithdraw(account, withdrawRequest.getAmount());

    }

    public void doDeposit(Account account, double amount) {

        account.setBalance(account.getBalance() + amount);

        String transactionDescriptions = "Deposit "+amount+" to "+account.getUser().getEmail();

        transactionRepository.save(Transaction.builder()
                .transactionType(TransactionType.DEPOSIT)
                .amount(amount)
                .description(transactionDescriptions)
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .account(account)
                .build());
    }

    public void doWithdraw(Account account, double amount) {
        if (account.getBalance() < amount) {
            throw new IllegalArgumentException("The balance isn't enough to withdraw "+amount);
        }
        account.setBalance(account.getBalance() - amount);
        String transactionDescriptions = "Withdraw "+amount+" from "+account.getUser().getEmail();

        transactionRepository.save(Transaction.builder()
                .transactionType(TransactionType.WITHDRAW)
                .amount(amount)
                .description(transactionDescriptions)
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .account(account)
                .build());
    }

    private Account validateAccountForDeposit(DepositRequest depositRequest) {

        String cardNumber =depositRequest.getCardNumber();
        boolean isAccountExists =accountRepository.existsByCardNumber(cardNumber);
        if(!isAccountExists){
            throw new IllegalArgumentException("The card with number "+cardNumber+" isn't exist.");
        }
        Account account = accountRepository.findByCardNumber(cardNumber);
        if (!account.getStatus() || !account.getUser().getStatus()){
            throw new IllegalArgumentException("The card with number "+cardNumber+" isn't valid right now.");
        }
        return account;
    }

    private Account validateAccountForWithdraw(WithdrawRequest withdrawRequest) {
        String CVV = withdrawRequest.getCvv();
        String cardNumber =withdrawRequest.getCardNumber();
        System.out.println("CVV: " + CVV);
        System.out.println("Card Number: " + cardNumber);
        boolean isAccountExists =accountService.isAccountExistsByCardNumberAndCVV(cardNumber,CVV);
        if(!isAccountExists){
            throw new IllegalArgumentException("There is a wrong in the card number: "+cardNumber+" or in the CVV: "+CVV);
        }
        Account account = accountRepository.findByCardNumber(cardNumber);
        if (!account.getStatus() || !account.getUser().getStatus()){
            throw new IllegalArgumentException("The card with number "+cardNumber+" isn't valid right now.");
        }
        return account;
    }
}
