package com.example.SmartAgriConnect.DTO;

import com.example.SmartAgriConnect.model.Produit;
import com.example.SmartAgriConnect.model.Stock;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.crypto.Data;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StockDto {
    private Integer quantiteDisponible;

    private Integer seuilCritique;

    private Data derniereMiseAJour;
    private String statut;

    public Stock toEntity(StockDto dto){

        if (dto==null){
            return null;
        }
        Stock stock=new Stock();

        stock.setQuantiteDisponible(dto.getQuantiteDisponible());
        stock.setSeuilCritique(dto.getSeuilCritique());
        stock.setDerniereMiseAJour(dto.getDerniereMiseAJour());
        stock.setStatut(dto.getStatut());
        return  stock;
    }

    public StockDto toDto(Stock stock){

        if (stock==null){
            return null;
        }
        StockDto dto=new StockDto();

        dto.setQuantiteDisponible(stock.getQuantiteDisponible());
        dto.setSeuilCritique(stock.getSeuilCritique());
        dto.setDerniereMiseAJour(stock.getDerniereMiseAJour());
        dto.setStatut(stock.getStatut());
        return dto;
    }
}
