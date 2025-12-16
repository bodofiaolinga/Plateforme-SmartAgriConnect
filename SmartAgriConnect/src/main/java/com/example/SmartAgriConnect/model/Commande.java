package com.example.SmartAgriConnect.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "commandes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Commande {

    private  String id;

    private String reference;

    private  Integer quantiteCommandee;

    private Integer prixTotal;

    private LocalDateTime dateCommande;

    private String status;
    @ManyToOne
    @JoinColumn(name = "produit_id")
    private Produit produit;
    @ManyToOne
    @JoinColumn(name = "agriculteur_id")
    private Agriculteur agriculteur;
    @ManyToOne
    @JoinColumn(name = "stock_id")
    private Stock stock;


}
