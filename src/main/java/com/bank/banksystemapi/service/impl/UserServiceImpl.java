package com.bank.banksystemapi.service.impl;

import com.bank.banksystemapi.entity.User;
import com.bank.banksystemapi.mapper.UserMapper;
import com.bank.banksystemapi.model.UserActivityResponse;
import com.bank.banksystemapi.model.UserResponse;
import com.bank.banksystemapi.repository.UserRepository;
import com.bank.banksystemapi.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public User findUserByEmail(String email) {
        userRepository.findByEmail(email);
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("user not found"));
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getCurrentUser() {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        return findUserByEmail(userEmail);
    }

    @Override
    public boolean existsUserEmailOrPhone(String email, String phoneNumber) {
        return userRepository.existsByEmail(email) || userRepository.existsByPhoneNumber(phoneNumber);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();

        return users.stream()
                .map(userMapper::toUserResponse)
                .toList();
    }

    @Override
    public UserResponse getProfileInfo() {
        User user = getCurrentUser();
        return userMapper.toUserResponse(user);
    }

    @Override
    public UserActivityResponse deactivateMyUser() {
        User user = getCurrentUser();
        user.setStatus(false);
        userRepository.save(user);
        return UserActivityResponse.builder()
                .message("User is deactivated, Login again to activate it")
                .build();
    }
}
