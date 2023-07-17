package com.jawbr.service;

import com.jawbr.entity.User;
import com.jawbr.repository.AuthRoleRepository;
import com.jawbr.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final AuthRoleRepository authRoleRepository;

    public UserService(UserRepository userRepository, AuthRoleRepository authRoleRepository) {
        this.userRepository = userRepository;
        this.authRoleRepository = authRoleRepository;
    }

    public User getByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
