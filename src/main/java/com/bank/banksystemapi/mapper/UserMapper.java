package com.bank.banksystemapi.mapper;

import com.bank.banksystemapi.entity.User;
import com.bank.banksystemapi.model.UserResponse;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponse toUserResponse(User user) {
        return UserResponse.builder()
                .id(String.valueOf(user.getId()))
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .city(user.getAddress())
                .phoneNumber(user.getPhoneNumber())
                .status(user.getStatus())
                .build();
    }
}
