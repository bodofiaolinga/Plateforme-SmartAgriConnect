package com.example.SmartAgriConnect.DTO.Reponse;

import com.example.SmartAgriConnect.model.Commande;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommandeReponseDto {


    private  Integer quantiteCommandee;

    private Integer prixTotal;

    private String status;


    private String produitId;




    public static CommandeReponseDto fromEntity(Commande commande){

        if (commande==null){
            return null;
        }
       CommandeReponseDto dto=new CommandeReponseDto();

        dto.setQuantiteCommandee(commande.getQuantiteCommandee());
        dto.setPrixTotal(commande.getPrixTotal());

        dto.setStatus(commande.getStatus());
        return dto;
    }
}
