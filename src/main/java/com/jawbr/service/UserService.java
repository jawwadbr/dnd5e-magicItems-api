package com.jawbr.service;

import com.jawbr.dto.AuthRoleDTO;
import com.jawbr.dto.UserDTO;
import com.jawbr.dto.request.UserRequest;
import com.jawbr.entity.AuthRole;
import com.jawbr.entity.User;
import com.jawbr.exception.IntegrityConstraintViolationException;
import com.jawbr.repository.AuthRoleRepository;
import com.jawbr.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final AuthRoleRepository authRoleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, AuthRoleRepository authRoleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.authRoleRepository = authRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void save(UserRequest registerRequest) {
        isUsernameTaken(registerRequest);
        userRepository.save(mapToEntity(registerRequest));
    }

    public Page<UserDTO> findAllUsers(Integer page, Integer pageSize, String sortBy) {
        pageSize = Math.min(Optional.ofNullable(pageSize).orElse(15), 200);
        sortBy = Optional.ofNullable(sortBy)
                .filter(s -> !s.isEmpty())
                .orElse("id");

        Page<User> userList = Optional.of(
                userRepository.findAll(PageRequest.of(
                        Optional.ofNullable(page).orElse(0),
                        pageSize,
                        Sort.Direction.ASC,
                        sortBy
                )))
                .filter(list -> !list.isEmpty())
                .orElseThrow(() -> new UsernameNotFoundException("No users found."));

        return userList.map(this::mapToDto);
    }

    public UserDTO findByUsername(String username) {
        User user = Optional.ofNullable(
                        userRepository.findByUsername(username))
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Username '%s' not found!", username)));
        return mapToDto(user);
    }

    // Utility method
    private UserDTO mapToDto(User user) {

        Collection<AuthRole> roles = user.getRoles();
        Collection<AuthRoleDTO> mappedRoles = new ArrayList<>();

        for(AuthRole tempRole : roles) {
            mappedRoles.add(AuthRoleDTO.builder()
                    .role(tempRole.getRole())
                    .build());
        }

        return UserDTO.builder()
                .username(user.getUsername())
                .roles(mappedRoles)
                .active(user.isActive())
                .createdAt(user.getCreatedAt())
                .build();
    }

    private User mapToEntity(UserRequest registerRequest) {
        User user = User.builder()
                .username(registerRequest.username())
                .active(true)
                .roles(authRoleRepository.findByRole("ROLE_USER"))
                .build();
        user.setPassword(passwordEncoder.encode(registerRequest.password()));
        return user;
    }

    private void isUsernameTaken(UserRequest userRequest) {
        if(Optional.ofNullable(userRepository.findByUsername(userRequest.username())).isPresent()) {
            throw new IntegrityConstraintViolationException(
                    String.format("Username '%s' is taken!", userRequest.username()));
        }
    }
}
