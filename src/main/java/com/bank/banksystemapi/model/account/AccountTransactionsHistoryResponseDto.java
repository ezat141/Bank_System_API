package com.bank.banksystemapi.model.account;

import com.bank.banksystemapi.entity.enumTypes.TransactionType;
import lombok.*;

import java.sql.Timestamp;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountTransactionsHistoryResponseDto {
    private Timestamp createdAt;
    private Double amount;
    private TransactionType transactionType;
    private String description;
}
