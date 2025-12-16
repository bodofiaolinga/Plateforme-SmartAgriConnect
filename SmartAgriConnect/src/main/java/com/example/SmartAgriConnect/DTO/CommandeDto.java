package com.example.SmartAgriConnect.DTO;

import com.example.SmartAgriConnect.model.Agriculteur;
import com.example.SmartAgriConnect.model.Commande;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.crypto.Data;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommandeDto {

    private String reference;

    private  Integer quantiteCommandee;

    private Integer prixTotal;

    private Data dateCommande;

    private String status;

    public Commande toEntity(CommandeDto dto){

        if (dto==null){
            return null;
        }
        Commande commande=new Commande();

        commande.setReference(dto.getReference());
        commande.setQuantiteCommandee(dto.getQuantiteCommandee());
        commande.setPrixTotal(dto.getPrixTotal());
        commande.setDateCommande(dto.getDateCommande());
        commande.setStatus(dto.getStatus());
        return  commande;

    }

    public CommandeDto toDto(Commande commande){

        if (commande==null){
            return null;
        }
       CommandeDto dto=new CommandeDto();
        dto.setReference(commande.getReference());
        dto.setQuantiteCommandee(commande.getQuantiteCommandee());
        dto.setPrixTotal(commande.getPrixTotal());
        dto.setDateCommande(commande.getDateCommande());
        dto.setStatus(commande.getStatus());
        return dto;
    }
}
