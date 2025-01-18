package com.bank.banksystemapi.controller;

import com.bank.banksystemapi.model.UserActivityResponse;
import com.bank.banksystemapi.model.UserResponse;
import com.bank.banksystemapi.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/bank/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
    }

    @GetMapping()
    public ResponseEntity<UserResponse> getUserInfo() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.getProfileInfo());
    }

    @PutMapping
    public ResponseEntity<UserActivityResponse> deactivateMyUser(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.deactivateMyUser());

    }





}
