package com.example.SmartAgriConnect.DTO.Request;

import com.example.SmartAgriConnect.model.Commande;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommandeRequestDto {

    private  Integer quantiteCommandee;

    private String produitId;


    public static Commande toEntity(CommandeRequestDto dto){

        if (dto==null){
            return null;
        }
        Commande commande=new Commande();
        commande.setQuantiteCommandee(dto.getQuantiteCommandee());

        return  commande;

    }
}
