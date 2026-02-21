package com.example.SmartAgriConnect.DTO.Request;

import com.example.SmartAgriConnect.model.Agriculteur;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgriculteurRequestDto {
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String localisation;


    public static Agriculteur toEntity(AgriculteurRequestDto dto){

        if (dto==null){
            return null;
        }
        Agriculteur agriculteur=new Agriculteur();

        agriculteur.setNom(dto.getNom());
        agriculteur.setPrenom(dto.getPrenom());
        agriculteur.setEmail(dto.getEmail());
        agriculteur.setTelephone(dto.getTelephone());
        agriculteur.setLocalisation(dto.getLocalisation());
        return agriculteur;

    }



}
