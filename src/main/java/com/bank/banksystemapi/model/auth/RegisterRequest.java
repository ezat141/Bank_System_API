package com.bank.banksystemapi.model.auth;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {

    @NotEmpty(message = "First name shouldn't be empty.")
    @Size(min = 2, message = "First name must be at-least 2 characters.")
    private String firstName;

    @NotEmpty(message = "Last name shouldn't be empty.")
    private String lastName;

    @NotEmpty(message = "Email shouldn't be empty.")
    @Email(message = "Email should be valid.")
    private String email;

    @NotEmpty(message = "Password shouldn't be empty.")
    @Size(min = 8, message = "Password should be 8 characters or more.")
    private String password;

    @NotEmpty(message = "Phone number shouldn't be empty.")
    @Size(min = 11,max = 11, message = "Phone number should be 11 numbers.")
    @Pattern(regexp = "^[0-9]+$",message = "Only numbers allowed for phone number.")
    private String phoneNumber;

    @NotEmpty(message = "Address shouldn't be empty.")
    private String address;
}
