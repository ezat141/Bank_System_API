package com.bank.banksystemapi.controller;

import com.bank.banksystemapi.model.account.AccountActivityResponse;
import com.bank.banksystemapi.model.account.AccountResponse;
import com.bank.banksystemapi.model.account.AccountTransactionsHistoryResponseDto;
import com.bank.banksystemapi.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/bank/account")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }


    @PostMapping
    public ResponseEntity<AccountResponse> createAccount(){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(accountService.createAccount());
    }

    @GetMapping
    public ResponseEntity<List<AccountResponse>> getUserAccounts(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(accountService.getUserAccounts());
    }

//    @GetMapping("/{accountId}")
//    public ResponseEntity<List<AccountTransactionsHistoryResponseDto>> getAccountHistory(@PathVariable Long accountId){
//        List<AccountTransactionsHistoryResponseDto> transactionHistory = accountService.getUserAccountTransactions(accountId);
//        return ResponseEntity.status(HttpStatus.OK)
//                .body(transactionHistory);
//    }

    @GetMapping("/transactions")
    public ResponseEntity<List<AccountTransactionsHistoryResponseDto>> getAccountHistory(){
        List<AccountTransactionsHistoryResponseDto> transactionHistory = accountService.getUserAccountTransactions();
        return ResponseEntity.status(HttpStatus.OK)
                .body(transactionHistory);
    }

    @PutMapping(value = "/{cardNumber}", params = "activate")
    public ResponseEntity<AccountActivityResponse> activateMyAccount(@Valid @PathVariable String cardNumber){
        return ResponseEntity.status(HttpStatus.OK)
                .body(accountService.activateAccount(cardNumber));
    }

    @PutMapping(value = "/{cardNumber}", params = "deactivate")
    public ResponseEntity<AccountActivityResponse> deactivateMyAccount(@Valid @PathVariable String cardNumber){
        return ResponseEntity.status(HttpStatus.OK)
                .body(accountService.deactivateAccount(cardNumber));
    }

    @GetMapping("/balance")
    public ResponseEntity<Double> viewCurrentAccountBalance(){
        return new ResponseEntity<>(accountService.viewAuthenticatedAccountBalance(), HttpStatus.OK);
    }

    @GetMapping("/cardNumber")
    public ResponseEntity<String> getCurrentAccountCardNumber(){
        return ResponseEntity.ok(accountService.getAuthenticatedAccountCardNumber());
    }




}
