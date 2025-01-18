package com.bank.banksystemapi.service;

import com.bank.banksystemapi.model.auth.AuthenticationRequest;
import com.bank.banksystemapi.model.auth.AuthenticationResponse;
import com.bank.banksystemapi.model.auth.RegisterRequest;

public interface AuthenticationService {
    AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);
    AuthenticationResponse register(RegisterRequest registerRequest);
}
