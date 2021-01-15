package com.rest.workshop.services;

import com.rest.workshop.domain.entitties.user.Role;
import com.rest.workshop.domain.entitties.user.User;
import com.rest.workshop.presentation.dtos.user.CreateUserDto;
import com.rest.workshop.presentation.dtos.user.UpdateEnabledDto;
import com.rest.workshop.presentation.dtos.user.UserDto;
import com.rest.workshop.presentation.dtos.user.UserListingDto;
import org.modelmapper.ModelMapper;

import java.util.List;

public interface UserService {
    UserDto create(ModelMapper modelMapper, CreateUserDto createUserDto);

    User findById(Long id);

    void delete(User user);

    List<UserListingDto> findAll(ModelMapper modelMapper);

    User getCurrentUser();

    void save(User currentUser);

    List<User> findAllByRole(Role role);

    UserDto updateEnabled(ModelMapper modelMapper, Long userId, UpdateEnabledDto updates);
}
