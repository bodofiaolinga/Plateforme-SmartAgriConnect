package com.example.SmartAgriConnect.service;

import com.example.SmartAgriConnect.DTO.auth.AuthReponse;
import com.example.SmartAgriConnect.DTO.auth.LoginRequest;
import com.example.SmartAgriConnect.DTO.auth.RegisterRequest;
import com.example.SmartAgriConnect.model.Agriculteur;
import com.example.SmartAgriConnect.model.EtatCompte;
import com.example.SmartAgriConnect.model.User;
import com.example.SmartAgriConnect.repository.UserRepository;
import com.example.SmartAgriConnect.security.JwtService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    public JwtService jwtService;

    @Autowired
    public PasswordEncoder passwordEncoder;

    @Autowired
    public UserRepository userRepository;


    public AuthReponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())){
            throw new RuntimeException("Email déjà utilisé !");
        }


        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        Agriculteur agriculteur = new Agriculteur();
        agriculteur.setNom(request.getName());
        agriculteur.setEmail(request.getEmail());
        agriculteur.setTelephone(String.valueOf(request.getPhoneNumber()));
        agriculteur.setEtatCompte(EtatCompte.ACTIF);

        agriculteur.setUser(user);
        user.setAgriculteur(agriculteur);

        User saveUser = userRepository.save(user);

        String token = jwtService.generateToken(saveUser);
        return new AuthReponse(token, saveUser.getEmail(), saveUser.getRole(),saveUser.getAgriculteur().getId());
    }

    public AuthReponse login(LoginRequest request){
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("email introuvable!"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new RuntimeException("mot de passe incorrect");
        }

        String token = jwtService.generateToken(user);
        return new AuthReponse(token, user.getEmail(), user.getRole(),user.getAgriculteur().getId());
    }
}
