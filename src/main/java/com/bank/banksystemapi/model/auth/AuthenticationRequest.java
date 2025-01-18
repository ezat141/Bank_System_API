package com.bank.banksystemapi.model.auth;
import lombok.*;
import jakarta.validation.constraints.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthenticationRequest {

    @NotEmpty(message = "Email shouldn't be empty.")
    @Email(message = "Email should be valid.")
    private String email;

    @NotEmpty(message = "Password shouldn't be empty.")
    private String password;
}
