package com.rest.workshop.services.implementation.user;

import com.rest.workshop.domain.entitties.user.Role;
import com.rest.workshop.domain.entitties.user.User;
import com.rest.workshop.presentation.dtos.user.CreateUserDto;
import com.rest.workshop.presentation.dtos.user.UpdateEnabledDto;
import com.rest.workshop.presentation.dtos.user.UserDto;
import com.rest.workshop.presentation.dtos.user.UserListingDto;
import com.rest.workshop.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto create(ModelMapper modelMapper, CreateUserDto createUserDto) {
        User newUser = modelMapper.map(createUserDto, User.class);
        newUser.setRole(Role.USER);
        save(newUser);
        return modelMapper.map(newUser, UserDto.class);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public List<UserListingDto> findAll(ModelMapper modelMapper) {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> modelMapper.map(user, UserListingDto.class)).collect(Collectors.toList());
    }

    @Override
    public User getCurrentUser() {
        String username = getCurrentUsername();

        return userRepository.findByUsername(username).orElseThrow(() -> new AccessDeniedException("Access is denied"));
    }

    @Override
    public void save(User currentUser) {
        userRepository.save(currentUser);
    }

    @Override
    public List<User> findAllByRole(Role role) {
        return userRepository.findAllByRole(role);
    }

    @Override
    public UserDto updateEnabled(ModelMapper modelMapper, Long userId, UpdateEnabledDto updates) {
        User user = findById(userId);
        modelMapper.map(updates, user);
        userRepository.save(user);
        return modelMapper.map(user, UserDto.class);
    }

    private String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AccessDeniedException("Access is denied");
        }

        return SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
    }
}
