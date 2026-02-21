package com.example.SmartAgriConnect.DTO.Request;

import com.example.SmartAgriConnect.model.Produit;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProduitRequestDto {

    private String nom;

    private String description;
    private String categories;
    private String uniteMesure;

    private Integer prixVente;

    public static Produit toEntity(ProduitRequestDto dto){

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
}
