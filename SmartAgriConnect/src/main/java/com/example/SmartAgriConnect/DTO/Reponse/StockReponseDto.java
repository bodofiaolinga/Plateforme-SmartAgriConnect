package com.example.SmartAgriConnect.DTO.Reponse;

import com.example.SmartAgriConnect.model.StatutStock;
import com.example.SmartAgriConnect.model.Stock;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockReponseDto {


  private Integer quantiteDisponible;

   private Integer seuilCritique;

    private String unite;

    private StatutStock statut;

    private String produitId;




    public static StockReponseDto fromEntity(Stock stock){

        if (stock==null){
            return null;
        }
        StockReponseDto dto=new StockReponseDto();

        dto.setQuantiteDisponible(stock.getQuantiteDisponible());
        dto.setSeuilCritique(stock.getSeuilCritique());
        dto.setUnite(stock.getUnite());
        dto.setStatut(stock.getStatut());
        return dto;
    }
}
