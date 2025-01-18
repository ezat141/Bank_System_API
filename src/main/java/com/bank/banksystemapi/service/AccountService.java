package com.bank.banksystemapi.service;

import com.bank.banksystemapi.entity.Account;
import com.bank.banksystemapi.model.account.AccountActivityResponse;
import com.bank.banksystemapi.model.account.AccountResponse;
import com.bank.banksystemapi.model.account.AccountTransactionsHistoryResponseDto;

import java.util.List;

public interface AccountService {
    AccountResponse createAccount();
    List<AccountResponse> getUserAccounts();
    List<AccountTransactionsHistoryResponseDto> getUserAccountTransactions(Long id);
    AccountActivityResponse deactivateAccount(String cardNumber);
    AccountActivityResponse activateAccount(String cardNumber);
    boolean isAccountExistsByCardNumberAndCVV(String cardNumber,String cvv);
    boolean isAccountExistsByCardNumber(String cardNumber);
    Account findAccountById(Long accountId);

}
