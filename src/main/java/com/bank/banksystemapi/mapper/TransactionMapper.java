package com.bank.banksystemapi.mapper;

import com.bank.banksystemapi.entity.Transaction;
import com.bank.banksystemapi.model.account.AccountTransactionsHistoryResponseDto;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {
    public AccountTransactionsHistoryResponseDto toResponse(Transaction transaction) {
        return new AccountTransactionsHistoryResponseDto(
                transaction.getCreatedAt(),
                transaction.getAmount(),
                transaction.getTransactionType(),
                transaction.getDescription()
        );
    }
}
