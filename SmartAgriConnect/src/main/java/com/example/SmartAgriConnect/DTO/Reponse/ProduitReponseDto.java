package com.example.SmartAgriConnect.DTO.Reponse;

import com.example.SmartAgriConnect.model.Produit;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProduitReponseDto {

    private String nom;

    private String description;
    private String categories;
    private String uniteMesure;

    private Integer prixVente;




    public static ProduitReponseDto fromEntity(Produit produit){

        if (produit==null){
            return null;
        }
        ProduitReponseDto dto=new ProduitReponseDto();

        dto.setNom(produit.getNom());
        dto.setDescription(produit.getDescription());
        dto.setCategories(produit.getCategories());
        dto.setUniteMesure(produit.getUniteMesure());
        dto.setPrixVente(produit.getPrixVente());
        return dto;
    }
}
