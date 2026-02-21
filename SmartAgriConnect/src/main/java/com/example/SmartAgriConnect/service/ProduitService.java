package com.example.SmartAgriConnect.service;

import com.example.SmartAgriConnect.DTO.Reponse.ProduitReponseDto;
import com.example.SmartAgriConnect.DTO.Request.ProduitRequestDto;

import java.util.UUID;

public interface ProduitService {

   ProduitReponseDto save(ProduitRequestDto dto);

    ProduitReponseDto findId(UUID id);

    ProduitReponseDto update(UUID id, ProduitRequestDto dto);

    void delete(UUID id);
}
