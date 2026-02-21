package com.example.SmartAgriConnect.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Table(name = "produits")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Produit extends AbstractEntity{

    private String nom;

    private String description;

    private String categories;

    private String uniteMesure;

    private Integer prixVente;

    @ManyToOne
    @JoinColumn(name = "agriculteur_id")
    private Agriculteur agriculteur;

    @OneToMany(mappedBy = "produit")
    private List<Commande> commande;

    @OneToOne(mappedBy = "produit")
    private Stock stock;
}
