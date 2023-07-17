package com.jawbr.controller;

import com.jawbr.dto.UserDTO;
import com.jawbr.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public Page<UserDTO> findAll(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer pageSize,
            @RequestParam(required = false) String sortBy)
    {
        return userService.findAllUsers(page, pageSize, sortBy);
    }

    @GetMapping("/{username}")
    public UserDTO findByUsername(@PathVariable String username) {
        return userService.findByUsername(username);
    }
}
