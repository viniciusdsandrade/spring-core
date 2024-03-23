package com.restful.studentmanagement.service.impl;

import com.restful.studentmanagement.dto.UserDto;
import com.restful.studentmanagement.entity.User;
import com.restful.studentmanagement.exception.EmailAlreadyExistsException;
import com.restful.studentmanagement.exception.ResourceNotFoundException;
import com.restful.studentmanagement.mapper.AutoUserMapper;
import com.restful.studentmanagement.repository.UserRepository;
import com.restful.studentmanagement.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {

        Optional<User> userOptional = userRepository.findByEmail(userDto.getEmail());

        if (userOptional.isPresent())
            throw new EmailAlreadyExistsException("Email already exists");
        
        User user = AutoUserMapper.MAPPER.mapToUser(userDto);
        User savedUser = userRepository.save(user);

        return AutoUserMapper.MAPPER.mapToUserDto(savedUser);
    }

    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("User", "id", id)
                );

        return AutoUserMapper.MAPPER.mapToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();

        return users.stream()
                .map(AutoUserMapper.MAPPER::mapToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto user) {
        User userToUpdate = userRepository.findById(user.getId())
                .orElseThrow(
                        () -> new ResourceNotFoundException("User", "id", user.getId())
                );

        userToUpdate.setFirstName(user.getFirstName());
        userToUpdate.setLastName(user.getLastName());
        userToUpdate.setEmail(user.getEmail());

        User updatedUser = userRepository.save(userToUpdate);

        return AutoUserMapper.MAPPER.mapToUserDto(updatedUser);
    }

    @Override
    public void deleteUserById(Long id) {

        User userToDelete = userRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("User", "id", id)
                );

        userRepository.deleteById(id);
    }
}
