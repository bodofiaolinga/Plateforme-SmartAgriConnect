package com.example.SmartAgriConnect.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;


@Table(name = "commandes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Commande extends AbstractEntity{



    private  Integer quantiteCommandee;

    private Integer prixTotal;

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
