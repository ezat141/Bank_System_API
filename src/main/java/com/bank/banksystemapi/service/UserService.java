package com.bank.banksystemapi.service;

import com.bank.banksystemapi.entity.User;
import com.bank.banksystemapi.model.UserActivityResponse;
import com.bank.banksystemapi.model.UserResponse;

import java.util.List;

public interface UserService {
    User findUserByEmail(String email);
    User saveUser(User user);
    User getCurrentUser();
    boolean existsUserEmailOrPhone(String email, String phoneNumber);
    List<UserResponse> getAllUsers();
    UserResponse getProfileInfo();
//    UserActivityResponse deactivateMyUser();
    UserActivityResponse deactivateMyUser(Long id);
    UserActivityResponse activateMyUser(Long id);

}
