package com.example.SmartAgriConnect.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "agriculteurs")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Agriculteur {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String nom;

    private String prenom;

    private String email;

    private String telephone;

    private String localisation;

    @OneToOne
    @JoinColumn(
            name = "user_id",
            unique = true,
            nullable = false
    )
    private User user;

    @OneToMany(mappedBy = "agriculteur")
    private Produit produit;

     @OneToMany(mappedBy = "agriculteur")
    private Stock stock;

    @OneToMany(mappedBy = "agriculteur")
     private Commande commande;
}
