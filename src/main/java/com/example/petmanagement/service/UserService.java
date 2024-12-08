package com.example.petmanagement.service;

import com.example.petmanagement.entity.User;

public interface UserService {
    User createUser(User user) ;
    User updateUserPassword(Long id, String newPassword);
    User toggleUserUnlockedStatus(Long id);
    void deleteUser(Long id) ;
}
