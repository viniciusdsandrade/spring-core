package com.restful.studentmanagement.service;

import com.restful.studentmanagement.entity.User;

import java.util.List;

public interface UserService {
    
    User createUser(User user);
    
    User getUserById(Long id);
    
    List<User> getAllUsers();
    
    User updateUser(User user);
    
    void deleteUserById(Long id);
}