package com.bank.banksystemapi.model;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserActivityResponse {
    private String message;
    private Timestamp timestamp;

}
