package com.restful.studentmanagement.service;

import com.restful.studentmanagement.dto.UserDto;
import com.restful.studentmanagement.entity.User;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto user);

    UserDto getUserById(Long id);
    
    List<UserDto> getAllUsers();

    UserDto updateUser(UserDto user);
    
    void deleteUserById(Long id);
}