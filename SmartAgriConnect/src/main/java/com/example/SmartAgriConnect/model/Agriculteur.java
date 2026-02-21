package com.example.SmartAgriConnect.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Table(name = "agriculteurs")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Agriculteur extends AbstractEntity {

    private String nom;

    private String prenom;

    private String email;

    private String telephone;

    private String localisation;

    @Enumerated(EnumType.STRING)
    private EtatCompte etatCompte;

    @OneToOne
    @JoinColumn(
            name = "user_id",
            unique = true,
            nullable = false
    )
    private User user;

    @OneToMany(mappedBy = "agriculteur")
    private List<Produit>  produit;

     @OneToMany(mappedBy = "agriculteur")
    private List<Stock> stock;

    @OneToMany(mappedBy = "agriculteur")
     private List <Commande> commande;
}
