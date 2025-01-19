package com.bank.banksystemapi.model.transaction;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WithdrawRequest {
    @Size(min = 16, max = 16, message = "The card number should be 16 numbers.")
    @Pattern(regexp = "^[0-9]+$", message = "The card number should be only numbers.")
    private String cardNumber;

    @Size(min = 4, max = 4 , message = "The CVV should be 4 numbers.")
    @Pattern(regexp = "^[0-9]+$", message = "The CVV should be only numbers.")
    private String cvv;

    @Positive(message = "The amount should be positive.")
    @Min(value = 5, message = "The amount should be greater than 5.")
    private double amount;




}
