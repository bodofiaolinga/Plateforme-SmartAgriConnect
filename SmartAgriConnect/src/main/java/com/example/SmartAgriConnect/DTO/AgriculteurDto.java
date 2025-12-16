package com.example.SmartAgriConnect.DTO;

import com.example.SmartAgriConnect.model.Agriculteur;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AgriculteurDto {

    private String nom;

    private String prenom;

    private String email;

    private String telephone;

    private String localisation;

    public Agriculteur toEntity(AgriculteurDto dto){

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

    public AgriculteurDto toDto(Agriculteur agriculteur){

        if (agriculteur==null){
            return null;
        }
        AgriculteurDto dto=new AgriculteurDto();
        dto.setNom(agriculteur.getNom());
        dto.setPrenom(agriculteur.getPrenom());
        dto.setEmail(agriculteur.getEmail());
        dto.setTelephone(agriculteur.getTelephone());
        dto.setLocalisation(agriculteur.getLocalisation());
        return dto;
    }

}

