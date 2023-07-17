package com.jawbr.controller;

import com.jawbr.dto.request.LoginRequest;
import com.jawbr.security.jwt.JwtService;
import com.jawbr.security.jwt.JwtUserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final JwtUserService jwtUserService;
    private final JwtService jwtService;

    public AuthenticationController(JwtUserService jwtUserService, JwtService jwtService) {
        this.jwtUserService = jwtUserService;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {

        jwtUserService.verifyUserCredentials(loginRequest);

        // generate token
        String token = jwtService.generateToken(loginRequest.username());

        return new ResponseEntity<>(token, HttpStatus.OK);

    }
}
