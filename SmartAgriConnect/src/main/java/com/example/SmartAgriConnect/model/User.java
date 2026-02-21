package com.example.SmartAgriConnect.model;

import jakarta.persistence.*;
import lombok.*;


@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User extends AbstractEntity{

    private String name;

    private String email;

    private String password;

    private int phoneNumber;

    @Enumerated(EnumType.STRING)
    private Role role=Role.AGRICULTEUR;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Agriculteur agriculteur;

}
