package com.example.SmartAgriConnect.controller;

import com.example.SmartAgriConnect.DTO.auth.AuthReponse;
import com.example.SmartAgriConnect.DTO.auth.LoginRequest;
import com.example.SmartAgriConnect.DTO.auth.RegisterRequest;
import com.example.SmartAgriConnect.service.AuthService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
@NoArgsConstructor
public class AuthController {
@Autowired
    public  AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthReponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthReponse>login(LoginRequest request){
        return ResponseEntity.ok(authService.login(request));
    }
}
