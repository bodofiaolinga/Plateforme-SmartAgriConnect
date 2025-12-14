package com.example.SmartAgriConnect.controller;

import com.example.SmartAgriConnect.DTO.AuthReponse;
import com.example.SmartAgriConnect.DTO.LoginRequest;
import com.example.SmartAgriConnect.DTO.RegisterRequest;
import com.example.SmartAgriConnect.service.AuthService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    public final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthReponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthReponse>login(LoginRequest request){
        return ResponseEntity.ok(authService.login(request));
    }
}
