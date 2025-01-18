package com.bank.banksystemapi.model.error;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Error {
    private HttpStatus httpStatus;
    private String message;
    private Timestamp timestamp;
}
