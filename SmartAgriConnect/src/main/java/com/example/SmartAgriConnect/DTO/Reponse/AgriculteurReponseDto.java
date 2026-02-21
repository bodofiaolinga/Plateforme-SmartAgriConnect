package com.example.SmartAgriConnect.DTO.Reponse;

import com.example.SmartAgriConnect.model.Agriculteur;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgriculteurReponseDto {

    private String nom;

    private String prenom;
    private String email;

    private String telephone;

    private String localisation;


    public static AgriculteurReponseDto fromEntity(Agriculteur agriculteur){

        if (agriculteur==null){
            return null;
        }
        AgriculteurReponseDto dto=new AgriculteurReponseDto();
        dto.setNom(agriculteur.getNom());
        dto.setPrenom(agriculteur.getPrenom());
        dto.setEmail(agriculteur.getEmail());
        dto.setTelephone(agriculteur.getTelephone());
        dto.setLocalisation(agriculteur.getLocalisation());
        return dto;
    }

}

