package com.bank.banksystemapi.mapper;

import com.bank.banksystemapi.entity.User;
import com.bank.banksystemapi.entity.enumTypes.RoleType;
import com.bank.banksystemapi.model.auth.RegisterRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class AuthenticationMapper {
    private final PasswordEncoder passwordEncoder;

    public AuthenticationMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User toEntity(RegisterRequest registerRequest){
        return User.builder()
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .email(registerRequest.getEmail())
                .phoneNumber(registerRequest.getPhoneNumber())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .roleType(RoleType.USER)
                .address(registerRequest.getAddress())
                .status(true)
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .build();
    }
}
