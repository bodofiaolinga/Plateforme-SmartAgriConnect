package com.example.SmartAgriConnect.DTO.Request;

import com.example.SmartAgriConnect.DTO.Reponse.StockReponseDto;
import com.example.SmartAgriConnect.model.StatutStock;
import com.example.SmartAgriConnect.model.Stock;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockRequestDto {

    private Integer quantiteDisponible;

    private Integer seuilCritique;

    private String unite;

    private StatutStock statut;


    private String produitId;



    public static Stock toEntity(StockRequestDto dto){

        if (dto==null){
            return null;
        }
        Stock stock=new Stock();

        stock.setQuantiteDisponible(dto.getQuantiteDisponible());
        stock.setSeuilCritique(dto.getSeuilCritique());
        stock.setStatut(dto.getStatut());
        stock.setUnite(dto.getUnite());
        return  stock;
    }


}
