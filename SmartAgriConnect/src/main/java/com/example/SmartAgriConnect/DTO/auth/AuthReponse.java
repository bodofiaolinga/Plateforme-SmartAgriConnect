package com.example.SmartAgriConnect.DTO.auth;
import com.example.SmartAgriConnect.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class AuthReponse {

    private String token;
    private String email;
    private Role role;
    private UUID agriculteurId;

}





