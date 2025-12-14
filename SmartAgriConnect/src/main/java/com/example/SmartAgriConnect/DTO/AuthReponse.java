package com.example.SmartAgriConnect.DTO;
import com.example.SmartAgriConnect.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthReponse {

    private String token;
    private String email;
    private Role role;

}





