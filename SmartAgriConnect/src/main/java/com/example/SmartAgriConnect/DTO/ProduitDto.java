package com.example.SmartAgriConnect.DTO;

import com.example.SmartAgriConnect.model.Commande;
import com.example.SmartAgriConnect.model.Produit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProduitDto {

    private String nom;

    private String description;
    private String categories;
    private String uniteMesure;

    private Integer prixVente;

    public Produit toEntity(ProduitDto dto){

        if (dto==null){
            return null;
        }
        Produit produit=new Produit();

        produit.setNom(dto.getNom());
        produit.setDescription(dto.getDescription());
        produit.setCategories(dto.getCategories());
        produit.setUniteMesure(dto.getUniteMesure());
        produit.setPrixVente(dto.getPrixVente());
        return  produit;

    }

    public ProduitDto toDto(Produit produit){

        if (produit==null){
            return null;
        }
        ProduitDto dto=new ProduitDto();

        dto.setNom(produit.getNom());
        dto.setDescription(produit.getDescription());
        dto.setCategories(produit.getCategories());
        dto.setUniteMesure(produit.getUniteMesure());
        dto.setPrixVente(produit.getPrixVente());
        return dto;
    }
}
