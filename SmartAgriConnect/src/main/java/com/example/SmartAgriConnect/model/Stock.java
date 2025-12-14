package com.example.SmartAgriConnect.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.crypto.Data;
@Entity
@Table(name = "stocks")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Stock {
    private String id;

    private Integer quantiteDisponible;

    private Integer seuilCritique;

    private Data derniereMiseAJour;
    private String statut;
    @OneToOne
    @JoinColumn(name = "produit_id",
                unique = true,
                nullable = false)
    private Produit produit;

    @ManyToOne
    @JoinColumn(name = "agriculteur_id")
    private Agriculteur agriculteur;
     @OneToMany(mappedBy = "stock")
    private Commande commande;

}
