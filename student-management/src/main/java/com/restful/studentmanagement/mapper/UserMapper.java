package com.restful.studentmanagement.mapper;

import com.restful.studentmanagement.dto.UserDto;
import com.restful.studentmanagement.entity.User;

public class UserMapper {

    // Map User to UserDto
    public static UserDto mapUserToUserDto(User user) {
        return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()
        );
    }

    // Map UserDto to User
    public static User mapUserDtoToUser(UserDto userDto) {
        return new User(
                userDto.getId(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getEmail()
        );
    }
}
