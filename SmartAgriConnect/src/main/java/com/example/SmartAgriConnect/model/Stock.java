package com.example.SmartAgriConnect.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import javax.xml.crypto.Data;
import java.time.LocalDateTime;
import java.util.List;


@Table(name = "stocks")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Stock extends AbstractEntity{

    private Integer quantiteDisponible;

    private Integer seuilCritique;

    private String unite;


     @Enumerated(EnumType.STRING)
    private StatutStock statut=StatutStock.NORMAL;


    @OneToOne
    @JoinColumn(name = "produit_id",
                unique = true,
                nullable = false)
    private Produit produit;

    @ManyToOne
    @JoinColumn(name = "agriculteur_id")
    private Agriculteur agriculteur;
     @OneToMany(mappedBy = "stock")
    private List<Commande>  commande;

}
