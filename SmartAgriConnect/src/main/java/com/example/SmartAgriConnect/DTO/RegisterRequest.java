package com.example.SmartAgriConnect.DTO;
import com.example.SmartAgriConnect.model.Role;
import com.example.SmartAgriConnect.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterRequest {

    private String name;
    private String email;
    private String password;
    private Role role;


    public User toEntity(RegisterRequest dto){
        User user=new User();

        if (dto==null) return null;
        user.setName(dto.name);
        user.setEmail(dto.email);
        user.setPassword(dto.password);
        user.setRole(dto.role);
        return user;
    }

    public RegisterRequest toDto(User user){
        if (user==null){
            return null;
        }
        RegisterRequest request=new RegisterRequest();
        request.setName(user.getName());
        request.setEmail(user.getEmail());
        request.setPassword(user.getPassword());
        request.setRole(user.getRole());
        return request;
    }

}
