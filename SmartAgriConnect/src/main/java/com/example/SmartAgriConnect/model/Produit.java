package com.example.SmartAgriConnect.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "produits")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Produit {
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String nom;

    private String description;
    private String categories;
    private String uniteMesure;

    private Integer prixVente;

    @ManyToOne
    @JoinColumn(name = "agriculteur_id")
    private Agriculteur agriculteur;

    @OneToMany(mappedBy = "produit")
    private Commande commande;
}
