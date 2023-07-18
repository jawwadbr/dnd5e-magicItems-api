package com.jawbr.controller;

import com.jawbr.dto.AuthResponseDTO;
import com.jawbr.dto.request.UserRequest;
import com.jawbr.security.jwt.JwtService;
import com.jawbr.security.jwt.JwtUserService;
import com.jawbr.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final JwtUserService jwtUserService;
    private final JwtService jwtService;
    private final UserService userService;

    public AuthenticationController(JwtUserService jwtUserService, JwtService jwtService, UserService userService) {
        this.jwtUserService = jwtUserService;
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody UserRequest loginRequest) {
        jwtUserService.verifyUserCredentials(loginRequest);
        // generate token
        String token = jwtService.generateToken(loginRequest.username());
        return new ResponseEntity<>(new AuthResponseDTO(token), HttpStatus.OK);
    }

    // By default, all new user have USER role
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserRequest registerRequest) {
        userService.save(registerRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
