package com.bank.banksystemapi.controller;

import com.bank.banksystemapi.model.transaction.DepositRequest;
import com.bank.banksystemapi.model.transaction.WithdrawRequest;
import com.bank.banksystemapi.service.TransactionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/bank/transaction")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/deposit")
    public ResponseEntity<String> deposit(@Valid @RequestBody DepositRequest depositRequest) {
        transactionService.deposit(depositRequest);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Succeed to deposit "+ depositRequest.getAmount()+" to card number "+ depositRequest.getCardNumber());
    }

    @PostMapping("/withdraw")
    public ResponseEntity<String> withdraw(@Valid @RequestBody WithdrawRequest withdrawRequest) {
        transactionService.withdraw(withdrawRequest);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Succeed to withdraw "+ withdrawRequest.getAmount()+" from card number "+ withdrawRequest.getCardNumber());
    }
}
