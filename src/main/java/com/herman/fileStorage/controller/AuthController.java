package com.herman.fileStorage.controller;

import com.herman.fileStorage.dto.UserLoginDto;
import com.herman.fileStorage.entity.User;
import com.herman.fileStorage.security.JwtService;
import com.herman.fileStorage.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final JwtService jwtService;

    public AuthController(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginDto dto){
        User user = userService.authenticate(dto.username(), dto.password());

        String token = jwtService.generateToken(user.getUsername());

        return ResponseEntity.ok(Map.of("token", token));
    }
}
