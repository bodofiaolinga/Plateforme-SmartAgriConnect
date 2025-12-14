package com.example.SmartAgriConnect.service;

import com.example.SmartAgriConnect.DTO.AuthReponse;
import com.example.SmartAgriConnect.DTO.LoginRequest;
import com.example.SmartAgriConnect.DTO.RegisterRequest;
import com.example.SmartAgriConnect.model.User;
import com.example.SmartAgriConnect.repository.UserRepository;
import com.example.SmartAgriConnect.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    public final JwtService jwtService;
    public final  PasswordEncoder passwordEncoder;
    public final UserRepository userRepository;
    public final RegisterRequest registerRequest;

    public AuthReponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())){
            throw new RuntimeException("Email déjà utilisé !");
        }
        User user=registerRequest.toEntity(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        User saveUser=userRepository.save(user);

        String token=jwtService.generateToken(saveUser);
        return new AuthReponse(token,saveUser.getEmail(),saveUser.getRole());

    }

    public AuthReponse login(LoginRequest request){
       User user=userRepository.findByEmail(request.getEmail())
               .orElseThrow(()-> new RuntimeException("email introuvable!"));

       if (!passwordEncoder.matches(request.getPassword(),user.getPassword())){
           throw new RuntimeException("mot de passe incorrect");
       }

       String token=jwtService.generateToken(user);
       return new AuthReponse(token, user.getEmail(), user.getRole());
    }




}
