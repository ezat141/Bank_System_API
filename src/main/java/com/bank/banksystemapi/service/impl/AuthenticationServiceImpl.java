package com.bank.banksystemapi.service.impl;

import com.bank.banksystemapi.entity.User;
import com.bank.banksystemapi.mapper.AuthenticationMapper;
import com.bank.banksystemapi.model.auth.AuthenticationRequest;
import com.bank.banksystemapi.model.auth.AuthenticationResponse;
import com.bank.banksystemapi.model.auth.RegisterRequest;
import com.bank.banksystemapi.security.JWTService;
import com.bank.banksystemapi.service.AuthenticationService;
import com.bank.banksystemapi.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserService userService;
    private final AuthenticationMapper authenticationMapper;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationServiceImpl(UserService userService, AuthenticationMapper authenticationMapper, JWTService jwtService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authenticationMapper = authenticationMapper;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public AuthenticationResponse register(RegisterRequest registerRequest) {
        if(userService.existsUserEmailOrPhone(registerRequest.getEmail(), registerRequest.getPhoneNumber())){
            throw new IllegalArgumentException("The email or phone number already exists.");
        }

        User user = authenticationMapper.toEntity(registerRequest);
        userService.saveUser(user);
        String token = jwtService.generateToken(user);

        sendValidationEmail(user);

        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }

    private void sendValidationEmail(User user) {
        //createAndSaveToken()
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        Authentication authenticate= authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword())
        );

        User user = ((User)authenticate.getPrincipal());
        String token = jwtService.generateToken(user);
        System.out.println(token);

        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }
}
