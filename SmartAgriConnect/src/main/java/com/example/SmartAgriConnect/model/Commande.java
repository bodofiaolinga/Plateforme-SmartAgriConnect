package com.example.SmartAgriConnect.model;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import javax.xml.crypto.Data;

public class Commande {

    private  String id;

    private String reference;

    private  Integer quantiteCommandee;

    private Integer prixTotal;

    private Data dateCommande;

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
