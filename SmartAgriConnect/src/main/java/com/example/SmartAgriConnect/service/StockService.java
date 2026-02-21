package com.example.SmartAgriConnect.service;

import com.example.SmartAgriConnect.DTO.Reponse.StockReponseDto;
import com.example.SmartAgriConnect.DTO.Request.StockRequestDto;
import com.example.SmartAgriConnect.model.StatutStock;

import java.nio.file.AccessDeniedException;
import java.util.UUID;

public interface StockService {
    StockReponseDto save(StockRequestDto dto) throws AccessDeniedException;

    StockReponseDto findId(UUID id);

    StockReponseDto update(UUID id, StockRequestDto dto);

    void delete(UUID id);

    StatutStock calculStatut(Integer quantite,Integer  seuilCritique);
}
