package com.bank.banksystemapi.model.transaction;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DepositRequest {

    @Positive(message = "The amount should be positive.")
    @Min(value = 5, message = "The amount should be greater than 5.")
    private double amount;

    @Size(min = 16, max = 16, message = "The card number should be 16 numbers.")
    @Pattern(regexp = "^[0-9]+$", message = "The card number should be only numbers.")
    private String cardNumber;

    @Positive(message = "The amount should be positive.")
    @Min(value = 5, message = "The amount should be greater than 5.")
    public double getAmount() {
        return amount;
    }

    @Size(min = 16, max = 16, message = "The card number should be 16 numbers.")
    @Pattern(regexp = "^[0-9]+$", message = "The card number should be only numbers.")
    public  String getCardNumber() {
        return cardNumber;
    }
}
