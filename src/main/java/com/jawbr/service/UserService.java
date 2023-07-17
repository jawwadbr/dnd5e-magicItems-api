package com.jawbr.service;

import com.jawbr.dto.request.UserRequest;
import com.jawbr.entity.User;
import com.jawbr.exception.IntegrityConstraintViolationException;
import com.jawbr.repository.AuthRoleRepository;
import com.jawbr.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // Utility method
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
